package com.book.service;

import com.book.dao.ItemCategoryDAO;
import com.book.dto.ItemCategoryDTO;
import com.book.mapper.ItemCategoryMapper;
import com.book.model.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCategoryService {

    private ItemCategoryDAO dao = new ItemCategoryDAO();

    /**
     * Generate next itemId like CAT001, CAT002, ...
     */
    public String generateNextItemId() {
        String last = dao.getLastItemId(); // may return null
        if (last == null) {
            return "CAT001";
        }
        // assume format "CAT###"
        String numPart = last.replaceAll("[^0-9]", ""); // keep digits
        int n = 0;
        try {
            n = Integer.parseInt(numPart);
        } catch (NumberFormatException e) {
            n = 0;
        }
        int next = n + 1;
        return String.format("CAT%03d", next);
    }

    public boolean addCategory(ItemCategoryDTO dto) {
        try {
            // create entity, set auto-generated itemId
            if (dto.getItemId() == null || dto.getItemId().isEmpty()) {
                dto.setItemId(generateNextItemId());
            }
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

    public ItemCategoryDTO getByItemId(String itemId) {
        ItemCategory entity = dao.findByItemId(itemId);
        return ItemCategoryMapper.toDTO(entity);
    }

    public boolean updateCategory(ItemCategoryDTO dto) {
        try {
            ItemCategory existing = dao.findByItemId(dto.getItemId());
            if (existing == null) return false;
            existing.setCategoryName(dto.getCategoryName());
            dao.update(existing);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(String itemId) {
        try {
            dao.deleteByItemId(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
