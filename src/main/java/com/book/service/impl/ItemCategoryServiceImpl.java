package com.book.service.impl;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.repository.ItemCategoryRepo;
import com.book.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private final ItemCategoryRepo categoryRepo;

    private ItemCategoryDTO mapToDTO(ItemCategory category) {
        return new ItemCategoryDTO(category.getName());
    }
    private ItemCategory mapToEntity(ItemCategoryDTO dto) {
        ItemCategory category = new ItemCategory();
        category.setName(dto.getName());
        return category;
    }

    @Override
    @Transactional
    public ItemCategory addCategory(ItemCategoryDTO dto) {
        ItemCategory category = new ItemCategory(null, dto.getName());
        return categoryRepo.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCategory> getAllCategories() {
        return categoryRepo.findAll();
    }
}
