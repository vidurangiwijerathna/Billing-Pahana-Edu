package com.book.mapper;

import com.book.dto.BillItemDTO;
import com.book.model.BillItem;
import com.book.model.Bill;

public class BillItemMapper {

    public static BillItemDTO toDTO(BillItem entity) {
        if (entity == null) return null;

        BillItemDTO dto = new BillItemDTO();
        dto.setId(entity.getId());
        dto.setBillId(entity.getBill() != null ? entity.getBill().getId() : null);
        dto.setItemId(entity.getItemId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    public static BillItem toEntity(BillItemDTO dto, Bill bill) {
        if (dto == null) return null;

        BillItem entity = new BillItem();
        entity.setId(dto.getId());
        entity.setBill(bill);  // set parent Bill entity
        entity.setItemId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());

        return entity;
    }
}
