// com/book/service/BillService.java
package com.book.service;

import com.book.dao.BillDAO;
import com.book.dao.CustomerDAO;
import com.book.dao.ItemDAO;
import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.model.Customer;
import com.book.model.Item;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class BillService {

    private final BillDAO billDAO = new BillDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();

    // Create bill: fetch unit prices from DB to prevent tampering; compute total; persist via DAO
    public boolean addBill(BillDTO dto) {
        double total = 0.0;
        if (dto.getBillItems() != null) {
            for (BillItemDTO it : dto.getBillItems()) {
                double price = itemDAO.getUnitPrice(it.getItemId());
                it.setPrice(price);
                total += price * it.getQuantity();
            }
        }
        dto.setTotalAmount(total);
        Integer id = billDAO.insert(dto);
        return id != null && id > 0;
    }

    public List<BillDTO> getAllBills() {
        return billDAO.findAll();
    }

    public BillDTO getBillById(Integer id) {
        return billDAO.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public List<Item> getAllItems() {
        // you already have ItemDAO.getAll()
        return itemDAO.getAll();
    }
}
