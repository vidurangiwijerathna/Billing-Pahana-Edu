package com.book.service;

import com.book.dao.CustomerDAO;
import com.book.dto.CustomerDTO;
import com.book.mapper.CustomerMapper;
import com.book.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void addCustomer(CustomerDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        customerDAO.save(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.findAll().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        return CustomerMapper.toDTO(customerDAO.findById(id));
    }

    public void updateCustomer(CustomerDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        customerDAO.update(customer);
    }

    public void deleteCustomer(Long id) {
        customerDAO.delete(id);
    }
}
