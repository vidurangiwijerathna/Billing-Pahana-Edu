package com.book.service;

import com.book.dao.ItemDAO;
import com.book.dao.ItemCategoryDAO;
import com.book.dto.ItemDTO;
import com.book.mapper.ItemMapper;
import com.book.model.Item;
import com.book.model.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ItemService {

    private final ItemDAO itemDAO = new ItemDAO();
    private final ItemCategoryDAO categoryDAO = new ItemCategoryDAO();

    public boolean addItem(ItemDTO dto) {
        try {
            ItemCategory category = categoryDAO.findById(dto.getCategoryId());
            Item entity = ItemMapper.toEntity(dto, category);
            itemDAO.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Item> getAllItems() {
        return itemDAO.findAll();
    }

    public ItemDTO getItemById(Long id) {
        return ItemMapper.toDTO(itemDAO.findById(id));
    }

    public boolean updateItem(ItemDTO dto) {
        try {
            ItemCategory category = categoryDAO.findById(dto.getCategoryId());
            Item entity = ItemMapper.toEntity(dto, category);
            itemDAO.update(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteItem(Long id) {
        try {
            itemDAO.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
