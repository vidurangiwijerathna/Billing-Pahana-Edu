package com.book.service;

import com.book.dao.ItemDAO;
import com.book.dto.ItemDTO;
import com.book.mapper.ItemMapper;
import com.book.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    public void createItem(ItemDTO dto) {
        Item item = ItemMapper.toEntity(dto);
        itemDAO.save(item);
    }

    public ItemDTO getItem(Long id) {
        Item item = itemDAO.findById(id);
        return item != null ? ItemMapper.toDTO(item) : null;
    }

    public List<ItemDTO> getAllItems() {
        return itemDAO.findAll()
                .stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void updateItem(ItemDTO dto) {
        Item item = ItemMapper.toEntity(dto);
        itemDAO.update(item);
    }

    public void deleteItem(Long id) {
        itemDAO.delete(id);
    }
}
