package com.book.mapper;

import com.book.dto.ItemCategoryDTO;
import com.book.model.ItemCategory;

public class ItemCategoryMapper {

    public static ItemCategoryDTO toDTO(ItemCategory entity) {
        if (entity == null) return null;
        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setId(entity.getId());
        dto.setCategoryName(entity.getCategoryName());
        return dto;
    }

    public static ItemCategory toEntity(ItemCategoryDTO dto) {
        if (dto == null) return null;
        ItemCategory entity = new ItemCategory();
        entity.setId(dto.getId());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }
}
