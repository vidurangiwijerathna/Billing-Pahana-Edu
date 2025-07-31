package com.book.service;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;

import java.sql.SQLException;
import java.util.List;

public interface ItemCategoryService {
    ItemCategory addCategory(ItemCategoryDTO dto) throws SQLException;

    List<ItemCategory> getAllCategories() throws SQLException;
}
