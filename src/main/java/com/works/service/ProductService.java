package com.works.service;

import com.works.dto.ProductSaveDto;
import com.works.entitiy.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Product save(ProductSaveDto productSaveDto) {
        Product product = modelMapper.map(productSaveDto, Product.class);
        return productRepository.save(product);
    }

}
