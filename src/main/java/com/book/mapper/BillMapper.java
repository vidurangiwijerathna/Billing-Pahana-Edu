package com.book.mapper;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.model.Bill;
import com.book.model.BillItem;

import java.util.ArrayList;
import java.util.List;

public class BillMapper {

    public static BillDTO toDTO(Bill bill) {
        if (bill == null) return null;

        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setCustomerId(bill.getCustomerId());
        dto.setCreatedAt(bill.getCreatedAt());  // java.sql.Timestamp
        dto.setTotalAmount(bill.getTotalAmount());
        dto.setCreatedBy(bill.getCreatedBy());

        List<BillItemDTO> billItemDTOs = new ArrayList<>();
        if (bill.getBillItems() != null) {
            for (BillItem item : bill.getBillItems()) {
                billItemDTOs.add(toBillItemDTO(item));
            }
        }
        dto.setBillItems(billItemDTOs);

        return dto;
    }

    public static Bill toEntity(BillDTO dto) {
        if (dto == null) return null;

        Bill bill = new Bill();
        bill.setId(dto.getId());
        bill.setCustomerId(dto.getCustomerId());
        bill.setCreatedAt(dto.getCreatedAt());  // java.sql.Timestamp
        bill.setTotalAmount(dto.getTotalAmount());
        bill.setCreatedBy(dto.getCreatedBy());

        List<BillItem> billItems = new ArrayList<>();
        if (dto.getBillItems() != null) {
            for (BillItemDTO itemDTO : dto.getBillItems()) {
                billItems.add(toBillItemEntity(itemDTO, bill));
            }
        }
        bill.setBillItems(billItems);

        return bill;
    }

    private static BillItemDTO toBillItemDTO(BillItem item) {
        if (item == null) return null;

        BillItemDTO dto = new BillItemDTO();
        dto.setId(item.getId());
        dto.setBillId(item.getBill() != null ? item.getBill().getId() : null);
        dto.setItemId(item.getItemId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());

        return dto;
    }

    private static BillItem toBillItemEntity(BillItemDTO dto, Bill bill) {
        if (dto == null) return null;

        BillItem item = new BillItem();
        item.setId(dto.getId());
        item.setBill(bill);
        item.setItemId(dto.getItemId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());

        return item;
    }
}
