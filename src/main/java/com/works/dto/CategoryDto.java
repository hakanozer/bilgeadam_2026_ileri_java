package com.works.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entitiy.Category}
 */
@Data
public class CategoryDto {
    private Long id;
    private String name;
}