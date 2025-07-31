package com.book.service;

import com.book.dto.ItemsDTO;
import com.book.entity.Items;

import java.util.List;

public interface ItemsService {
    Items addItem(ItemsDTO dto);
    Items updateItem(Long id, ItemsDTO dto);
    void deleteItem(Long id);
    List<Items> getAllItems();
}
