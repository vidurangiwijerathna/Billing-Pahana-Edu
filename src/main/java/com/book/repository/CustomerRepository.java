package com.book.repository;

import com.book.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByAccountNumber(String accountNumber);
}
