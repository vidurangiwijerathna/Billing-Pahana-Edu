package com.book.repository;

import com.book.entity.BillItem;
import java.util.List;

public interface BillItemRepository {
    BillItem save(BillItem billItem) throws Exception;
    BillItem update(BillItem billItem) throws Exception;
    boolean delete(int id) throws Exception;
    List<BillItem> findByBillId(int billId) throws Exception;
}
