package com.book.service.impl;

import com.book.dao.ItemCategoryDAO;
import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.service.ItemCategoryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryServiceImpl implements ItemCategoryService {

    private ItemCategoryDAO dao = new ItemCategoryDAO();

    private ItemCategoryDTO toDTO(ItemCategory entity) {
        if (entity == null) return null;
        return new ItemCategoryDTO(entity.getId(), entity.getName());
    }

    private ItemCategory toEntity(ItemCategoryDTO dto) {
        if (dto == null) return null;
        return new ItemCategory(dto.getId(), dto.getName());
    }

    @Override
    public ItemCategoryDTO saveCategory(ItemCategoryDTO dto) throws SQLException {
        ItemCategory saved = dao.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public ItemCategoryDTO getCategoryById(Long id) throws SQLException {
        return toDTO(dao.findById(id));
    }

    @Override
    public List<ItemCategoryDTO> getAllCategories() throws SQLException {
        List<ItemCategory> categories = dao.findAll();
        List<ItemCategoryDTO> dtos = new ArrayList<>();
        for (ItemCategory c : categories) {
            dtos.add(toDTO(c));
        }
        return dtos;
    }

    @Override
    public ItemCategoryDTO updateCategory(Long id, ItemCategoryDTO dto) throws SQLException {
        ItemCategory existing = dao.findById(id);
        if (existing == null) {
            throw new SQLException("Category not found");
        }
        existing.setName(dto.getName());
        dao.update(existing);
        return toDTO(existing);
    }

    @Override
    public void deleteCategory(Long id) throws SQLException {
        dao.delete(id);
    }
}
