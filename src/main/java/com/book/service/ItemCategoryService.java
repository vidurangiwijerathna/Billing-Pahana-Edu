package com.book.service;

import com.book.dao.ItemCategoryDAO;
import com.book.dto.ItemCategoryDTO;
import com.book.mapper.ItemCategoryMapper;
import com.book.model.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

import static com.book.mapper.ItemCategoryMapper.toEntity;

public class ItemCategoryService {

    private ItemCategoryDAO dao = new ItemCategoryDAO();

    public void addCategory(ItemCategoryDTO dto) {
        ItemCategory category = toEntity(dto);
        dao.save(category);
    }



    public List<ItemCategory> getAllCategories() {
        return dao.getAll();
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
