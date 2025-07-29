package com.book.service.impl;

import com.book.entity.BillItem;
import com.book.repository.BillItemRepository;
import com.book.service.BillItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillItemServiceImpl implements BillItemService {

    private final BillItemRepository billItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BillItem> getAllBillItems() {
        return billItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BillItem getBillItemById(Long id) {
        return billItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BillItem not found with ID: " + id));
    }

    @Override
    @Transactional
    public void deleteBillItem(Long id) {
        if (!billItemRepository.existsById(id)) {
            throw new RuntimeException("BillItem not found with ID: " + id);
        }
        billItemRepository.deleteById(id);
    }
}
