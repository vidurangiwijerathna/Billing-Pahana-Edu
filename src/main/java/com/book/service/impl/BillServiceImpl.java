package com.book.service.impl;


import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.entity.*;
import com.book.repository.*;
import com.book.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillItemRepository billItemRepository;
    private final ItemRepo itemRepo;

    @Override
    public Bill createBill(BillDTO dto) {
        List<BillItem> billItems = new ArrayList<>();
        double total = 0;

        for (BillItemDTO itemDto : dto.getItems()) {
            Items item = itemRepo.findById(itemDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            double subTotal = item.getPrice() * itemDto.getQuantity();
            total += subTotal;

            BillItem billItem = new BillItem();
            billItem.setItem(item);
            billItem.setQuantity(itemDto.getQuantity());
            billItem.setUnitPrice(item.getPrice());
            billItem.setSubTotal(subTotal);
            billItems.add(billItem);
        }

        Bill bill = new Bill();
        bill.setCustomerName(dto.getCustomerName());
        bill.setTotal(total);
        bill.setCreatedAt(LocalDateTime.now());
        bill.setItems(billItems);

        Bill savedBill = billRepository.save(bill);

        // Link each bill item to the saved bill
        for (BillItem item : billItems) {
            item.setBill(savedBill);
        }
        billItemRepository.saveAll(billItems);

        return savedBill;
    }
}
