package com.book.service;

import com.book.dao.BillDAO;
import com.book.dto.BillDTO;
import com.book.mapper.BillMapper;
import com.book.model.Bill;

import java.util.List;
import java.util.stream.Collectors;

public class BillService {

    private final BillDAO billDAO = new BillDAO();

    // Get all bills as DTOs
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billDAO.getAll();
        return bills.stream()
                .map(BillMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get single bill by ID as DTO
    public BillDTO getBillById(Integer id) {
        Bill bill = billDAO.findById(id);
        return BillMapper.toDTO(bill);
    }

    // Add a new bill from DTO
    public boolean addBill(BillDTO billDTO) {
        try {
            Bill bill = BillMapper.toEntity(billDTO);
            billDAO.save(bill);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
