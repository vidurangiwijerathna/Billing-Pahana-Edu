package com.book.service.impl;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.service.BillService;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {

    @Override
    public int createBill(BillDTO billDTO) throws Exception {
        Connection con = null;
        PreparedStatement psBill = null;
        PreparedStatement psBillItem = null;
        ResultSet generatedKeys = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String insertBillSQL = "INSERT INTO bill (customer_id, created_at) VALUES (?, ?)";
            psBill = con.prepareStatement(insertBillSQL, Statement.RETURN_GENERATED_KEYS);
            psBill.setInt(1, billDTO.getCustomerId());
            psBill.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            psBill.executeUpdate();

            generatedKeys = psBill.getGeneratedKeys();
            int billId = -1;
            if (generatedKeys.next()) {
                billId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating bill failed, no ID obtained.");
            }

            String insertBillItemSQL = "INSERT INTO bill_item (bill_id, item_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
            psBillItem = con.prepareStatement(insertBillItemSQL);

            for (BillItemDTO item : billDTO.getItems()) {
                psBillItem.setInt(1, billId);
                psBillItem.setInt(2, item.getItemId());
                psBillItem.setInt(3, item.getQuantity());
                psBillItem.setDouble(4, item.getUnitPrice());
                psBillItem.addBatch();
            }

            psBillItem.executeBatch();

            con.commit();

            return billId;
        } catch (Exception e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (psBill != null) psBill.close();
            if (psBillItem != null) psBillItem.close();
            if (con != null) con.setAutoCommit(true);
            if (con != null) con.close();
        }
    }

    @Override
    public List<BillDTO> getAllBills() throws Exception {
        List<BillDTO> bills = new ArrayList<>();
        String sql = "SELECT id, customer_id FROM bill ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int billId = rs.getInt("id");
                int customerId = rs.getInt("customer_id");

                List<BillItemDTO> items = getBillItemsByBillId(billId);

                BillDTO billDTO = new BillDTO(customerId, items);
                bills.add(billDTO);
            }
        }

        return bills;
    }

    private List<BillItemDTO> getBillItemsByBillId(int billId) throws Exception {
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
