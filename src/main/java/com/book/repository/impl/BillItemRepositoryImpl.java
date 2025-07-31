package com.book.repository.impl;

import com.book.entity.BillItem;
import com.book.repository.BillItemRepository;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillItemRepositoryImpl implements BillItemRepository {

    @Override
    public BillItem save(BillItem billItem) throws Exception {
        String sql = "INSERT INTO bill_item (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, billItem.getBillId());
            stmt.setInt(2, billItem.getItemId());
            stmt.setInt(3, billItem.getQuantity());
            stmt.setDouble(4, billItem.getPrice());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) throw new SQLException("Creating bill item failed, no rows affected.");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    billItem.setId(generatedKeys.getInt(1));
                }
            }
        }
        return billItem;
    }

    @Override
    public BillItem update(BillItem billItem) throws Exception {
        String sql = "UPDATE bill_item SET bill_id = ?, item_id = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, billItem.getBillId());
            stmt.setInt(2, billItem.getItemId());
            stmt.setInt(3, billItem.getQuantity());
            stmt.setDouble(4, billItem.getPrice());
            stmt.setInt(5, billItem.getId());

            stmt.executeUpdate();
        }
        return billItem;
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM bill_item WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<BillItem> findByBillId(int billId) throws Exception {
        List<BillItem> list = new ArrayList<>();
        String sql = "SELECT * FROM bill_item WHERE bill_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BillItem item = new BillItem();
                    item.setId(rs.getInt("id"));
                    item.setBillId(rs.getInt("bill_id"));
                    item.setItemId(rs.getInt("item_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    list.add(item);
                }
            }
        }
        return list;
    }
}
