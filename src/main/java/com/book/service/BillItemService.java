package com.book.service;

import com.book.dto.BillItemDTO;
import java.util.List;

public interface BillItemService {
    List<BillItemDTO> getBillItemsByBillId(int billId) throws Exception;
}
