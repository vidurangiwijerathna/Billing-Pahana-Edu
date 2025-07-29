package com.book.service;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
    ItemCategory addCategory(ItemCategoryDTO dto);
    List<ItemCategory> getAllCategories();
}
