package com.book.service.impl;

import com.book.dto.BillItemDTO;
import com.book.service.BillItemService;
import com.book.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillItemServiceImpl implements BillItemService {

    @Override
    public List<BillItemDTO> getBillItemsByBillId(int billId) throws Exception {
        List<BillItemDTO> items = new ArrayList<>();
        String sql = "SELECT item_id, quantity, unit_price FROM bill_item WHERE bill_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BillItemDTO item = new BillItemDTO();
                    item.setItemId(rs.getInt("item_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setUnitPrice(rs.getDouble("unit_price"));
                    items.add(item);
                }
            }
        }

        return items;
    }
}
