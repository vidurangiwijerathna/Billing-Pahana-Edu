package com.book.service;

import com.book.dto.BillDTO;
import java.util.List;

public interface BillService {
    void createBill(BillDTO billDTO) throws Exception; // ✅ Correct name
    List<BillDTO> getAllBills() throws Exception;
}
