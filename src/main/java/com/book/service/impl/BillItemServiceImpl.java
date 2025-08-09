package com.book.service.impl;

import com.book.dao.ItemsDAO;
import com.book.dto.ItemsDTO;
import com.book.entity.ItemCategory;
import com.book.entity.Items;
import com.book.service.ItemsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    private ItemsDAO itemsDAO = new ItemsDAO();

    @Override
    public Items addItem(ItemsDTO dto) {
        try {
            Items item = new Items();
            item.setName(dto.getName());
            item.setAuthor(dto.getAuthor());
            item.setPrice(dto.getPrice());
            item.setStock(dto.getStock());

            if (dto.getCategoryId() != 0) {
                ItemCategory category = new ItemCategory();
                category.setId(Long.valueOf(dto.getCategoryId()));
                item.setCategory(category);
            } else {
                item.setCategory(null);
            }

            return itemsDAO.save(item);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Items updateItem(Long id, ItemsDTO dto) {
        try {
            Items existingItem = itemsDAO.findById(id);
            if (existingItem == null) {
                return null;
            }

            existingItem.setName(dto.getName());
            existingItem.setAuthor(dto.getAuthor());
            existingItem.setPrice(dto.getPrice());
            existingItem.setStock(dto.getStock());

            if (dto.getCategoryId() != 0) {
                ItemCategory category = new ItemCategory();
                category.setId(Long.valueOf(dto.getCategoryId()));
                existingItem.setCategory(category);
            } else {
                existingItem.setCategory(null);
            }

            boolean success = itemsDAO.update(existingItem); // ✅ now returns boolean
            return success ? existingItem : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteItem(Long id) {
        try {
            itemsDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Items> getAllItems() {
        try {
            return itemsDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
