package com.book.repository;

import com.book.entity.Customer;
import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer) throws Exception;
    Customer update(Customer customer) throws Exception;
    void deleteById(Long id) throws Exception;
    Customer findById(Long id) throws Exception;
    List<Customer> findAll() throws Exception;
    boolean existsById(Long id) throws Exception;
    boolean existsByAccountNumber(String accountNumber) throws Exception;
}
