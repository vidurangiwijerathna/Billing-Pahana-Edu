package com.book.service;

import com.book.dto.BillDTO;
import java.util.List;

public interface BillService {
    int createBill(BillDTO billDTO) throws Exception;
    List<BillDTO> getAllBills() throws Exception;
}
