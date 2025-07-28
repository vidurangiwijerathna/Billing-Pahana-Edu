package com.book.service;

import com.book.dto.BillDTO;
import com.book.entity.Bill;

public interface BillService {
    Bill createBill(BillDTO dto);
}
