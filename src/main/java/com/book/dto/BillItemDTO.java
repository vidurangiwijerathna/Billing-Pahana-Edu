package com.book.dto;

public class BillItemDTO {
    private int id;
    private int billId;
    private int itemId;
    private int quantity;
    private double price;
    private double unitPrice;  // ✅ Add this

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getUnitPrice() { return unitPrice; }         // ✅ Getter
    public void setUnitPrice(double unitPrice) {               // ✅ Setter
        this.unitPrice = unitPrice;
    }
}
