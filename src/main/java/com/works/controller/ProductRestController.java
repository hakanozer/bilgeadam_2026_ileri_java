package com.works.controller;

import com.works.dto.ProductSaveDto;
import com.works.entitiy.Product;
import com.works.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("save")
    public Product save(@Valid @RequestBody ProductSaveDto productSaveDto) {
        return productService.save(productSaveDto);
    }


}
