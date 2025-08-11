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
            ItemCategory entity = ItemCategoryMapper.toEntity(dto);
            dao.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ItemCategoryDTO> getAllCategories() {
        List<ItemCategory> list = dao.getAll();
        return list.stream().map(ItemCategoryMapper::toDTO).collect(Collectors.toList());
    }

    public ItemCategoryDTO getById(Long id) {
        ItemCategory entity = dao.findById(id);
        return ItemCategoryMapper.toDTO(entity);
    }

    public boolean updateCategory(ItemCategoryDTO dto) {
        try {
            ItemCategory existing = dao.findById(dto.getId());
            if (existing == null) return false;
            existing.setCategoryName(dto.getCategoryName());
            dao.update(existing);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
