// src/com/book/service/impl/CustomerServiceImpl.java
package com.book.service.impl;

import com.book.dto.CustomerDTO;
import com.book.entity.Customer;
import com.book.repository.CustomerRepository;
import com.book.repository.impl.CustomerRepositoryImpl;
import com.book.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setAccountNumber(customer.getAccountNumber());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setTelephone(customer.getTelephone());
        return dto;
    }

    private Customer mapToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setAccountNumber(dto.getAccountNumber());
        customer.setName(dto.getName());
        customer.setAddress(dto.getAddress());
        customer.setTelephone(dto.getTelephone());
        return customer;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) throws Exception {
        if (customerRepository.existsByAccountNumber(dto.getAccountNumber())) {
            throw new RuntimeException("Account number already exists");
        }
        Customer saved = customerRepository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        return customerRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws Exception {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        return mapToDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) throws Exception {
        Customer existing = customerRepository.findById(id);
        if (existing == null) {
            throw new RuntimeException("Customer not found");
        }

        existing.setAccountNumber(dto.getAccountNumber());
        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setTelephone(dto.getTelephone());

        return mapToDTO(customerRepository.update(existing));
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
