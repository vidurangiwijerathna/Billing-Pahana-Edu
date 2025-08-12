package com.book.dto;

import java.sql.Timestamp;
import java.util.List;

public class BillDTO {
    private int id;
    private long customerId;
    private Timestamp createdAt;
    private double totalAmount;
    private int createdBy;
    private List<BillItemDTO> billItems;

    public BillDTO() {}

    public BillDTO(int id, long customerId, Timestamp createdAt, double totalAmount, int createdBy, List<BillItemDTO> billItems) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.createdBy = createdBy;
        this.billItems = billItems;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public List<BillItemDTO> getBillItems() { return billItems; }
    public void setBillItems(List<BillItemDTO> billItems) { this.billItems = billItems; }
}
