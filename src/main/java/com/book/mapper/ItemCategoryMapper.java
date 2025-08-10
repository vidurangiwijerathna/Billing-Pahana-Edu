package com.book.mapper;

import com.book.dto.ItemCategoryDTO;
import com.book.model.ItemCategory;

public class ItemCategoryMapper {

    public static ItemCategoryDTO toDTO(ItemCategory entity) {
        if (entity == null) return null;
        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setId(entity.getId());
        dto.setItemId(entity.getItemId());
        dto.setCategoryName(entity.getCategoryName());
        return dto;
    }

    public static ItemCategory toEntity(ItemCategoryDTO dto) {
        if (dto == null) return null;
        ItemCategory entity = new ItemCategory();
        // id may be null for new records
        entity.setId(dto.getId());
        entity.setItemId(dto.getItemId());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }
}
