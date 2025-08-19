package com.book.service;

import com.book.dao.CustomerDAO;
import com.book.dto.CustomerDTO;
import com.book.mapper.CustomerMapper;
import com.book.model.Customer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void addCustomer( String name,String email,String telephone, String address,  String accountNumber) {
        Customer c = new Customer();
        c.setName(name);
        c.setEmail(email);
        c.setPhone(telephone);
        c.setAddress(address);
        c.setAccountNumber(accountNumber);
        c.setCreatedAt(LocalDateTime.now());  // auto-set date/time
        customerDAO.save(c);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public List<Customer> viewAllCustomers() {
        return customerDAO.findAll();
    }
}
