package com.book.dto;

import lombok.*;
import jakarta.validation.constraints.*;  //  Add this import for validation annotations

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Author is required")
    private String author;

    @Positive(message = "Price must be positive")
    private double price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}
