package com.book.controller;


import com.book.dto.BillDTO;
import com.book.entity.Bill;
import com.book.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping(path = "/createBill")
    @PreAuthorize("hasRole('CASHIER')")
    public ResponseEntity<Bill> createBill(@RequestBody BillDTO dto) {
        return ResponseEntity.ok(billService.createBill(dto));
    }
}
