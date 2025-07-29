package com.book.controller;

import com.book.dto.ItemDTO;
import com.book.entity.Items;
import com.book.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/items")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping(path = "/itemcreate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Items> addItem(@Valid @RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.addItem(dto));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Items> updateItem(@PathVariable Long id, @Valid @RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.updateItem(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted");
    }

    @GetMapping
    public ResponseEntity<List<Items>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }
}
