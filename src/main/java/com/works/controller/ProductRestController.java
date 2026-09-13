package com.works.controller;

import com.works.dto.ProductResponseDto;
import com.works.dto.ProductSaveDto;
import com.works.entitiy.Product;
import com.works.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("save")
    public Product save(@Valid @RequestBody ProductSaveDto productSaveDto) {
        return productService.save(productSaveDto);
    }

    @PostMapping("saveAll")
    public List<Product> saveAll(@RequestBody List<@Valid ProductSaveDto> productSaveDtos) {
        return productService.saveAll(productSaveDtos);
    }

    @GetMapping("all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("search")
    public Page<Product> search(
            @RequestParam(value = "q", defaultValue = "") String q,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        return productService.search(q, page);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Long pid = productService.deleteById(id);
        if (pid == 0l){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Product Delete Success " + pid);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody ProductSaveDto productSaveDto){
        ProductResponseDto updatedProduct = productService.update(id, productSaveDto);
        if (updatedProduct == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }


}
