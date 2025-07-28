
package com.book.service.impl;

import com.book.dto.ItemDTO;
import com.book.entity.Items;
import com.book.entity.ItemCategory;
import com.book.repository.ItemCategoryRepo;
import com.book.repository.ItemRepo;
import com.book.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ItemServiceImpl implements ItemService {

        private final ItemRepo itemRepo;
        private final ItemCategoryRepo categoryRepo;

        @Override
        public Items addItem(ItemDTO dto) {
            ItemCategory category = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Items item = new Items(null, dto.getName(), dto.getAuthor(), dto.getPrice(), dto.getStock(), category);
            return itemRepo.save(item);
        }

        @Override
        public Items updateItem(Long id, ItemDTO dto) {
            Items item = itemRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            ItemCategory category = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            item.setName(dto.getName());
            item.setAuthor(dto.getAuthor());
            item.setPrice(dto.getPrice());
            item.setStock(dto.getStock());
            item.setCategory(category);

            return itemRepo.save(item);
        }

        @Override
        public void deleteItem(Long id) {
            if (!itemRepo.existsById(id)) {
                throw new RuntimeException("Item not found");
            }
            itemRepo.deleteById(id);
        }

        @Override
        public List<Items> getAllItems() {
            return itemRepo.findAll();
        }
    }

