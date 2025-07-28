package com.book.service;


import com.book.dto.ItemDTO;
import com.book.entity.Items;

import java.util.List;

public interface ItemService {
    Items addItem(ItemDTO dto);
    Items updateItem(Long id, ItemDTO dto);
    void deleteItem(Long id);
    List<Items> getAllItems();
}
