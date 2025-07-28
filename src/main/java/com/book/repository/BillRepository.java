package com.book.repository;

import com.book.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface BillRepository extends JpaRepository<Bill, Long> {
    }
