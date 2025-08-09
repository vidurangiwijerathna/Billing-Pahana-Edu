package com.book.service;

import com.book.dao.CustomerDAO;
import com.book.dto.CustomerDTO;
import com.book.model.Customer;
import java.util.List;


public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Assuming you already have a method like this
    public boolean addCustomer(CustomerDTO dto) {
        try {
            Customer customer = new Customer();
            customer.setName(dto.getName());
            customer.setEmail(dto.getEmail());
            customer.setPhone(dto.getPhone());
            customer.setAddress(dto.getAddress());
            customer.setAccountNumber(dto.getAccountNumber());

            customerDAO.save(customer);  // This method saves and might throw exception if fails

            return true; // success
        } catch (Exception e) {
            e.printStackTrace();
            return false; // failure
        }
    }

    public List<CustomerDTO> getAllCustomers() {
        // your existing method
        return null; // placeholder
    }
}
