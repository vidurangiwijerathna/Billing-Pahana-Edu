package com.book.service.impl;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.entity.Bill;
import com.book.entity.BillItem;
import com.book.entity.Items;
import com.book.exception.ResourceNotFoundException;
import com.book.repository.BillItemRepository;
import com.book.repository.BillRepository;
import com.book.repository.ItemRepo;
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

    @Override
    @Transactional
    public Bill createBill(BillDTO dto) {
        List<BillItem> billItems = new ArrayList<>();
        double total = 0;

        for (BillItemDTO itemDto : dto.getItems()) {
            Items item = itemRepo.findById(itemDto.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemDto.getItemId()));

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

        Bill savedBill = billRepository.save(bill);

        // Link each bill item to the saved bill before saving bill items
        for (BillItem billItem : billItems) {
            billItem.setBill(savedBill);
        }
        billItemRepository.saveAll(billItems);

        savedBill.setItems(billItems);

        return savedBill;
    }
}
