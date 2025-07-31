package com.book.service;

import com.book.dto.BillItemDTO;
import java.util.List;

public interface BillItemService {
    List<BillItemDTO> getBillItemsByBillId(int billId) throws Exception;
    BillItemDTO saveBillItem(BillItemDTO billItemDTO) throws Exception;
    BillItemDTO updateBillItem(BillItemDTO billItemDTO) throws Exception;
    boolean deleteBillItem(int id) throws Exception;
}
