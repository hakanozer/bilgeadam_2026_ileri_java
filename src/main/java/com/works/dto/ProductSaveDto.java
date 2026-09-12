package com.works.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entitiy.Product}
 */
@Value
public class ProductSaveDto implements Serializable {
    @NotNull
    @Size(min = 2, max = 100)
    @NotEmpty
    String title;
    @NotNull
    @Size(min = 2, max = 200)
    @NotEmpty
    String description;
    @NotNull
    @Min(1)
    @Max(1000000)
    Double price;
    @NotNull
    @NotEmpty
    String category;
}