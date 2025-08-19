package com.book.dto;

import com.book.model.Item;

public class ItemDTO {
    private Long id;
    private String name;
    private String author;
    private double price;
    private int stock;
    private Long categoryId;
    private String categoryName;

    public ItemDTO() {}

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.author = item.getAuthor();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.categoryId = item.getCategory().getId();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
