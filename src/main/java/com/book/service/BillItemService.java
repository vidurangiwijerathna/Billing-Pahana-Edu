package com.book.service;

import com.book.entity.BillItem;
import java.util.List;

public interface BillItemService {
    List<BillItem> getAllBillItems();
    BillItem getBillItemById(Long id);
    void deleteBillItem(Long id);
}
