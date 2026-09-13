package com.works.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.works.entitiy.Product}
 */
@Data
public class ProductResponseDto {
    private String title;
    private String description;
    private Double price;
    private List<CategoryDto> categories;
}