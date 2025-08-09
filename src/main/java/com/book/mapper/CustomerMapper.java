package com.book.mapper;

import com.book.dto.CustomerDTO;
import com.book.model.Customer;

public class CustomerMapper {

    // Entity -> DTO
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setAccountNumber(customer.getAccountNumber());
        return dto;
    }

    // DTO -> Entity
    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setAccountNumber(dto.getAccountNumber());
        return customer;
    }
}
