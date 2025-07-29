package com.book.controller;

import com.book.dto.ItemCategoryDTO;
import com.book.entity.ItemCategory;
import com.book.service.ItemCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ItemCategoryController {

    private final ItemCategoryService categoryService;

    @PostMapping(path = "/itemcategory")
    public ResponseEntity<ItemCategory> addCategory(@Valid @RequestBody ItemCategoryDTO dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

    @GetMapping(path = "/getitemcategory")
    public ResponseEntity<List<ItemCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
