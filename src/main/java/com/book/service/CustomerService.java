package com.book.service;

import com.book.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws Exception;
    List<CustomerDTO> getAllCustomers() throws Exception;
    CustomerDTO getCustomerById(Long id) throws Exception;
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws Exception;
    void deleteCustomer(Long id) throws Exception;
}
