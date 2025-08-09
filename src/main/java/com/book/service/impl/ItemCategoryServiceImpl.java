package com.book.service.impl;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.repository.ItemCategoryRepository;
import com.book.repository.impl.ItemCategoryRepositoryImpl;
import com.book.service.ItemCategoryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepository categoryRepo = new ItemCategoryRepositoryImpl();

    @Override
    public ItemCategoryDTO addCategory(ItemCategoryDTO dto) throws SQLException {
        ItemCategory category = new ItemCategory();
        category.setName(dto.getName());
        ItemCategory savedCategory = categoryRepo.save(category);

        ItemCategoryDTO savedDTO = new ItemCategoryDTO();
        savedDTO.setId(savedCategory.getId());
        savedDTO.setName(savedCategory.getName());

        return savedDTO;
    }

    @Override
    public List<ItemCategoryDTO> getAllCategories() throws SQLException {
        List<ItemCategory> categories = categoryRepo.findAll();
        List<ItemCategoryDTO> categoryDTOs = new ArrayList<>();

        for (ItemCategory category : categories) {
            ItemCategoryDTO dto = new ItemCategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            categoryDTOs.add(dto);
        }

        return categoryDTOs;
    }
}
