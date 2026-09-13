package com.works.service;

import com.works.dto.ProductResponseDto;
import com.works.dto.ProductSaveDto;
import com.works.entitiy.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = "products", allEntries = true)
    public Product save(ProductSaveDto productSaveDto) {
        Product product = modelMapper.map(productSaveDto, Product.class);
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<ProductSaveDto> productSaveDtos){
        List<Product> products = productSaveDtos.stream()
                .map(dto -> modelMapper.map(dto, Product.class))
                .toList();
        return productRepository.saveAll(products);
    }

    @Cacheable("products")
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Cacheable("products")
    public Page<Product> search(String q, int page){
        PageRequest pageable = PageRequest.of(page, 10);
        if (q.isEmpty()){
            return productRepository.findAll(pageable);
        }
        Page<Product> products = productRepository.findByTitleContainsOrDescriptionContainsAllIgnoreCase(q, q, pageable);
        return products;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Long deleteById(Long id){
        Optional<Product> pro = productRepository.findById(id);
        if (pro.isEmpty()){
            return 0l;
        }else {
            productRepository.deleteById(id);
            return id;
        }
    }

    public ProductResponseDto update(Long id, ProductSaveDto productSaveDto){
        Product product = productRepository.findById(id).orElse(null);
        if (product != null){
            Product pro = modelMapper.map(productSaveDto, Product.class);
            pro.setId(id);
            productRepository.save(pro);
            ProductResponseDto responseDto = modelMapper.map(pro, ProductResponseDto.class);
            return responseDto;
        }
        return null;
    }

}
