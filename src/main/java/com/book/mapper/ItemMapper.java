package com.book.mapper;

import com.book.dto.ItemDTO;
import com.book.model.Item;
import com.book.model.ItemCategory;

public class ItemMapper {

    public static ItemDTO toDTO(Item entity) {
        if (entity == null) return null;
        ItemDTO dto = new ItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthor(entity.getAuthor());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        if (entity.getCategory() != null) {
            dto.setCategoryId(entity.getCategory().getId());
            dto.setCategoryName(entity.getCategory().getCategoryName());
        }
        return dto;
    }

    public static Item toEntity(ItemDTO dto, ItemCategory category) {
        if (dto == null) return null;
        Item entity = new Item();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        entity.setCategory(category);
        return entity;
    }
}
