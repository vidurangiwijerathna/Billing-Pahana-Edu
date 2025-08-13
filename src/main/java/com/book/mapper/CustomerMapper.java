package com.book.mapper;

import com.book.dto.CustomerDTO;
import com.book.model.Customer;

public class CustomerMapper {

    // Entity -> DTO


    public static CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getAccountNumber(),
                customer.getCreatedAt()
        );
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
        customer.setCreatedAt(dto.getCreatedAt()); // NEW
        return customer;
    }
}
