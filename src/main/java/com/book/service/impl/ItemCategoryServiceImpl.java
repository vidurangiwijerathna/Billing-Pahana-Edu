package com.book.service.impl;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.repository.ItemCategoryRepository;
import com.book.repository.impl.ItemCategoryRepositoryImpl;
import com.book.service.ItemCategoryService;

import java.sql.SQLException;
import java.util.List;

public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository categoryRepo = new ItemCategoryRepositoryImpl();

    @Override
    public ItemCategory addCategory(ItemCategoryDTO dto) throws SQLException {
        ItemCategory category = new ItemCategory();
        category.setName(dto.getName());
        // Save category and return saved entity
        return categoryRepo.save(category);
    }

    @Override
    public List<ItemCategory> getAllCategories() throws SQLException {
        return categoryRepo.findAll();
    }
}
