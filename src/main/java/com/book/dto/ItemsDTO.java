package com.book.dto;

public class ItemsDTO {
    private Long id;
    private String name;
    private String author;
    private double price;
    private int stock;
    private ItemCategoryDTO category;
    private int categoryId;

    public ItemsDTO() {
    }

    public ItemsDTO(Long id, String name, String author, double price, int stock, ItemCategoryDTO category, int categoryId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ItemCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(ItemCategoryDTO category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
