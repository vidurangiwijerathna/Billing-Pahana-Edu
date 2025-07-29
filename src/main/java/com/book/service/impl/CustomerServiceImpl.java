package com.book.service.impl;

import com.book.dto.CustomerDTO;
import com.book.entity.Customer;
import com.book.repository.CustomerRepository;
import com.book.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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
    public CustomerDTO saveCustomer(CustomerDTO dto) {
        if (customerRepository.existsByAccountNumber(dto.getAccountNumber())) {
            throw new RuntimeException("Account number already exists");
        }
        Customer saved = customerRepository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existing.setAccountNumber(dto.getAccountNumber());
        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setTelephone(dto.getTelephone());

        return mapToDTO(customerRepository.save(existing));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
