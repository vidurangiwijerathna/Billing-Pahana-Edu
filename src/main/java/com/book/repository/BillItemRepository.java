package com.book.repository;

import com.book.entity.BillItem;
import java.util.List;

public interface BillItemRepository {
    BillItem save(BillItem billItem) throws Exception;
    BillItem update(BillItem billItem) throws Exception;
    void deleteById(Long id) throws Exception;
    BillItem findById(Long id) throws Exception;
    List<BillItem> findAll() throws Exception;
    boolean existsById(Long id) throws Exception;
}
