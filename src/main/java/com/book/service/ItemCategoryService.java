package com.book.service;

import com.book.dao.ItemCategoryDAO;
import com.book.dto.ItemCategoryDTO;
import com.book.mapper.ItemCategoryMapper;
import com.book.model.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCategoryService {

    private ItemCategoryDAO dao = new ItemCategoryDAO();

    public boolean addCategory(ItemCategoryDTO dto) {
        try {
            ItemCategory category = ItemCategoryMapper.toEntity(dto);
            dao.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ItemCategoryDTO> getAllCategories() {
        List<ItemCategory> entities = dao.findAll();
        return entities.stream()
                .map(ItemCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteCategory(Long id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
