package com.book.service;

import com.book.dto.ItemCategoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemCategoryService {

    ItemCategoryDTO saveCategory(ItemCategoryDTO dto) throws SQLException;
    ItemCategoryDTO getCategoryById(Long id) throws SQLException;
    List<ItemCategoryDTO> getAllCategories() throws SQLException;
    ItemCategoryDTO updateCategory(Long id, ItemCategoryDTO dto) throws SQLException;
    void deleteCategory(Long id) throws SQLException;
}
