package com.book.service.impl;

import com.book.dto.BillItemDTO;
import com.book.entity.BillItem;
import com.book.repository.BillItemRepository;
import com.book.repository.impl.BillItemRepositoryImpl;
import com.book.service.BillItemService;

import java.util.List;
import java.util.stream.Collectors;

public class BillItemServiceImpl implements BillItemService {

    private final BillItemRepository billItemRepository = new BillItemRepositoryImpl();

    private BillItemDTO mapToDTO(BillItem billItem) {
        BillItemDTO dto = new BillItemDTO();
        dto.setId(billItem.getId());
        dto.setBillId(billItem.getBillId());
        dto.setItemId(billItem.getItemId());
        dto.setQuantity(billItem.getQuantity());
        dto.setPrice(billItem.getPrice());  // Use getPrice() here
        return dto;
    }

    private BillItem mapToEntity(BillItemDTO dto) {
        BillItem entity = new BillItem();
        entity.setId(dto.getId());
        entity.setBillId(dto.getBillId());
        entity.setItemId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());  // Use setPrice()
        return entity;
    }

    @Override
    public List<BillItemDTO> getBillItemsByBillId(int billId) throws Exception {
        List<BillItem> billItems = billItemRepository.findByBillId(billId);
        return billItems.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BillItemDTO saveBillItem(BillItemDTO billItemDTO) throws Exception {
        BillItem saved = billItemRepository.save(mapToEntity(billItemDTO));
        return mapToDTO(saved);
    }

    @Override
    public BillItemDTO updateBillItem(BillItemDTO billItemDTO) throws Exception {
        BillItem updated = billItemRepository.update(mapToEntity(billItemDTO));
        return mapToDTO(updated);
    }

    @Override
    public boolean deleteBillItem(int id) throws Exception {
        return billItemRepository.delete(id);
    }
}
