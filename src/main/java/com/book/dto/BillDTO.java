package com.book.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    @NotEmpty(message = "Customer name is required")
    private String customerName;

    @NotEmpty(message = "Bill must contain at least one item")
    @Valid // Validate each BillItemDTO in the list
    private List<BillItemDTO> items;
}
