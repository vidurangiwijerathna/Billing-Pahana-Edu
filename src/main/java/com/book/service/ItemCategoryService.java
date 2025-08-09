package com.book.service;

import com.book.dto.ItemCategoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemCategoryService {
    ItemCategoryDTO addCategory(ItemCategoryDTO dto) throws SQLException;

    List<ItemCategoryDTO> getAllCategories() throws SQLException;
}
