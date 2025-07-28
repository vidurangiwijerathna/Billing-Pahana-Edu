package com.book.dto;


import lombok.*;

import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class BillDTO {
        private String customerName;
        private List<BillItemDTO> items;
    }

