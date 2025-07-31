package com.book.service.impl;

import com.book.dto.ItemsDTO;
import com.book.entity.Items;
import com.book.service.ItemsService;

import java.util.ArrayList;
import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    @Override
    public Items addItem(ItemsDTO dto) {
        // Logic to convert DTO to entity and save to DB
        Items item = new Items();
        item.setName(dto.getName());
        item.setAuthor(dto.getAuthor());
        item.setPrice(dto.getPrice());
        item.setStock(dto.getStock());
        // Set category, if applicable

        // Save to DB here using DAO (not shown)
        return item; // Return saved entity (possibly with ID)
    }

    @Override
    public Items updateItem(Long id, ItemsDTO dto) {
        // Your update logic
        return null;
    }

    @Override
    public void deleteItem(Long id) {
        // Your delete logic
    }

    @Override
    public List<Items> getAllItems() {
        // Your list fetch logic
        return new ArrayList<>();
    }
}
