package com.book.dto;


import lombok.*;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class BillItemDTO {
        private Long itemId;
        private int quantity;
    }

