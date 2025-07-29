package com.book.controller;

import com.book.entity.BillItem;
import com.book.service.BillItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/bill-items")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BillItemController {

    private final BillItemService billItemService;

    @GetMapping(path = "/getAllBillItems")
    @PreAuthorize("hasRole('CASHIER')")
    public ResponseEntity<List<BillItem>> getAllBillItems() {
        return ResponseEntity.ok(billItemService.getAllBillItems());
    }

    @GetMapping("/getBillItemById")
    @PreAuthorize("hasRole('CASHIER')")
    public ResponseEntity<BillItem> getBillItemById(@PathVariable Long id) {
        return ResponseEntity.ok(billItemService.getBillItemById(id));
    }

    @DeleteMapping("/deleteBillItem")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBillItem(@PathVariable Long id) {
        billItemService.deleteBillItem(id);
        return ResponseEntity.ok("BillItem deleted successfully");
    }
}
