package com.book.service.impl;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.entity.Bill;
import com.book.entity.BillItem;
import com.book.entity.Customer;
import com.book.entity.Users;
import com.book.repository.BillRepository;
import com.book.repository.impl.BillRepositoryImpl;
import com.book.service.BillService;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {

    private BillRepository billRepository = new BillRepositoryImpl();

    @Override
    public void createBill(BillDTO billDTO) throws Exception {
        Bill bill = new Bill();

        // Set customer reference (only ID)
        Customer customer = new Customer();
        customer.setId(billDTO.getCustomerId());
        bill.setCustomer(customer);

        // Set createdBy reference (only ID)
        Users createdBy = new Users();
        createdBy.setId(billDTO.getCreatedBy());
        bill.setCreatedBy(createdBy);

        bill.setTotalAmount(billDTO.getTotalAmount());

        // Map BillItems
        List<BillItem> billItems = new ArrayList<>();
        if (billDTO.getItems() != null) {
            for (BillItemDTO itemDTO : billDTO.getItems()) {
                BillItem item = new BillItem();

                item.setItemId(itemDTO.getItemId());
                item.setQuantity(itemDTO.getQuantity());
                item.setPrice(itemDTO.getPrice());

                // Set back reference to Bill
                item.setBill(bill);

                billItems.add(item);
            }
        }
        bill.setBillItems(billItems);

        // Save Bill + BillItems in one go
        billRepository.save(bill);
    }

    @Override
    public List<BillDTO> getAllBills() throws Exception {
        List<Bill> bills = billRepository.findAll();
        List<BillDTO> billDTOs = new ArrayList<>();

        for (Bill bill : bills) {
            BillDTO dto = new BillDTO();
            dto.setId(bill.getId());
            dto.setCustomerId(bill.getCustomer().getId());
            dto.setTotalAmount(bill.getTotalAmount());
            dto.setCreatedBy(bill.getCreatedBy().getId());
            dto.setBillDate(new java.sql.Timestamp(bill.getCreatedAt().getTime()));
            billDTOs.add(dto);
        }
        return billDTOs;
    }
}
