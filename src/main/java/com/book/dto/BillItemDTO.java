package com.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItemDTO {
    @NotNull(message = "Item ID is required")
    private Long itemId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
