package com.task.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "code is required")
    @Size(min = 5, max = 10, message = "code must be between 5 and 10 characters")
    private String code;
    @Positive(message = "price must be positive")
    private Double price;
}
