package com.book.dto;

import java.util.List;

public class BillDTO {
    private int customerId;
    private List<BillItemDTO> items;

    public BillDTO() {}

    public BillDTO(int customerId, List<BillItemDTO> items) {
        this.customerId = customerId;
        this.items = items;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<BillItemDTO> getItems() {
        return items;
    }
    public void setItems(List<BillItemDTO> items) {
        this.items = items;
    }
}
