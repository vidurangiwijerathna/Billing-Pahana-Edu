package com.book.dto;

import java.util.List;

public class BillDTO {
    private int customerId;
    private int createdBy;
    private double totalAmount;
    private List<BillItemDTO> items;

    public BillDTO() {}

    public BillDTO(int customerId, int createdBy, double totalAmount, List<BillItemDTO> items) {
        this.customerId = customerId;
        this.createdBy = createdBy;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BillItemDTO> getItems() {
        return items;
    }
    public void setItems(List<BillItemDTO> items) {
        this.items = items;
    }
}
