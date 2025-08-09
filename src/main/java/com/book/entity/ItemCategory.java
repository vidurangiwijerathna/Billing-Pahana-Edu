package com.book.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item_category")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public ItemCategory() {}

    public ItemCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters

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
}
