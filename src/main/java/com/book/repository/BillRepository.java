package com.book.repository;

import com.book.entity.Bill;

import java.util.List;

public interface BillRepository {
    void save(Bill bill) throws Exception;

    Bill findById(int id) throws Exception;

    List<Bill> findAll() throws Exception;

    boolean existsById(Long id) throws Exception;
}
