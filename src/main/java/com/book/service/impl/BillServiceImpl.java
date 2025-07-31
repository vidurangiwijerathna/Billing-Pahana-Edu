package com.book.service.impl;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.entity.Bill;
import com.book.repository.BillRepository;
import com.book.repository.impl.BillRepositoryImpl;
import com.book.service.BillService;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {

    private BillRepository billRepository = new BillRepositoryImpl();

    @Override
    public void createBill(BillDTO billDTO) throws Exception {
        // Your createBill code here (not shown)
    }

    @Override
    public List<BillDTO> getAllBills() throws Exception {
        List<Bill> bills = billRepository.findAll();
        List<BillDTO> billDTOs = new ArrayList<>();

        for (Bill bill : bills) {
            BillDTO dto = new BillDTO();
            dto.setCustomerId(bill.getCustomerId());


            billDTOs.add(dto);
        }

        return billDTOs;
    }
}
