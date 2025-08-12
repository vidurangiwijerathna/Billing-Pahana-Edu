package com.book.service;

import com.book.dao.BillItemDAO;
import com.book.dto.BillItemDTO;
import com.book.mapper.BillItemMapper;
import com.book.model.Bill;
import com.book.model.BillItem;

import java.util.List;
import java.util.stream.Collectors;

public class BillItemService {

    private final BillItemDAO billItemDAO = new BillItemDAO();

    public boolean addBillItem(BillItemDTO dto, Bill bill) {
        try {
            BillItem entity = BillItemMapper.toEntity(dto, bill);
            billItemDAO.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BillItemDTO> getItemsByBillId(Integer billId) {
        return billItemDAO.getByBillId(billId).stream()
                .map(BillItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean deleteItemsByBillId(Integer billId) {
        try {
            billItemDAO.deleteByBillId(billId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
