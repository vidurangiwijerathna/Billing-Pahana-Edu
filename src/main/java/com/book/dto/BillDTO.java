package com.book.dto;

import java.sql.Timestamp;
import java.util.List;

public class BillDTO {
    private Long id;
    private Long customerId;
    private Timestamp billDate;
    private double totalAmount;
    private Long createdBy;
    private List<BillItemDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getBillDate() {
        return billDate;
    }

    public void setBillDate(Timestamp billDate) {
        this.billDate = billDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public List<BillItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BillItemDTO> items) {
        this.items = items;
    }
}
