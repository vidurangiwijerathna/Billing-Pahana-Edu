package com.book.repository;

import com.book.entity.Bill;
import java.util.List;

public interface BillRepository {
    Bill save(Bill bill) throws Exception;
    Bill update(Bill bill) throws Exception;
    void deleteById(Long id) throws Exception;
    Bill findById(Long id) throws Exception;
    List<Bill> findAll() throws Exception;
    boolean existsById(Long id) throws Exception;
}
