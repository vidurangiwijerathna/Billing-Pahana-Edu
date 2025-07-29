package com.book.service.impl;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.entity.*;
import com.book.repository.*;
import com.book.service.BillService;
import jakarta.transaction.Transactional;
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
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Bill createBill(BillDTO dto) {
        List<BillItem> billItems = new ArrayList<>();
        double total = 0;

        for (BillItemDTO itemDto : dto.getItems()) {
            Items item = itemRepo.findById(itemDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemDto.getItemId()));

            double subTotal = item.getPrice() * itemDto.getQuantity();
            total += subTotal;

            BillItem billItem = new BillItem();
            billItem.setItem(item);
            billItem.setQuantity(itemDto.getQuantity());
            billItem.setUnitPrice(item.getPrice());
            billItem.setSubTotal(subTotal);

            billItems.add(billItem);
        }

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + dto.getCustomerId()));

        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setTotal(total);
        bill.setCreatedAt(LocalDateTime.now());

        Bill savedBill = billRepository.save(bill);

        for (BillItem billItem : billItems) {
            billItem.setBill(savedBill);
        }
        billItemRepository.saveAll(billItems);

        savedBill.setItems(billItems);

        return savedBill;
    }
}
