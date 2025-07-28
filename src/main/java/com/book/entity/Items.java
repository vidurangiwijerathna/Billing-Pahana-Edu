package com.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private double price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory category;
}
