package com.book.dto;

import lombok.*;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ItemDTO  {
        private String name;
        private String author;
        private double price;
        private int stock;
        private Long categoryId;
    }

