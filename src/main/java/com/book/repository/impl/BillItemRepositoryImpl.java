package com.book.repository.impl;

import com.book.entity.BillItem;
import com.book.entity.Bill;
import com.book.entity.Items;
import com.book.repository.BillItemRepository;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillItemRepositoryImpl implements BillItemRepository {

    @Override
    public BillItem save(BillItem billItem) throws Exception {
        String sql = "INSERT INTO bill_items (quantity, unit_price, sub_total, bill_id, item_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, billItem.getQuantity());
            stmt.setDouble(2, billItem.getUnitPrice());
            stmt.setDouble(3, billItem.getSubTotal());
            stmt.setLong(4, billItem.getBill().getId());
            stmt.setLong(5, billItem.getItem().getId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    billItem.setId(rs.getLong(1));
                }
            }
        }
        return billItem;
    }

    @Override
    public BillItem update(BillItem billItem) throws Exception {
        String sql = "UPDATE bill_items SET quantity=?, unit_price=?, sub_total=?, bill_id=?, item_id=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billItem.getQuantity());
            stmt.setDouble(2, billItem.getUnitPrice());
            stmt.setDouble(3, billItem.getSubTotal());
            stmt.setLong(4, billItem.getBill().getId());
            stmt.setLong(5, billItem.getItem().getId());
            stmt.setLong(6, billItem.getId());

            stmt.executeUpdate();
        }
        return billItem;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        String sql = "DELETE FROM bill_items WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public BillItem findById(Long id) throws Exception {
        String sql = "SELECT * FROM bill_items WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BillItem billItem = new BillItem();
                    billItem.setId(rs.getLong("id"));
                    billItem.setQuantity(rs.getInt("quantity"));
                    billItem.setUnitPrice(rs.getDouble("unit_price"));
                    billItem.setSubTotal(rs.getDouble("sub_total"));

                    Bill bill = new Bill();
                    bill.setId(rs.getLong("bill_id"));
                    billItem.setBill(bill);

                    Items item = new Items();
                    item.setId(rs.getLong("item_id"));
                    billItem.setItem(item);

                    return billItem;
                }
            }
        }
        return null;
    }

    @Override
    public List<BillItem> findAll() throws Exception {
        List<BillItem> list = new ArrayList<>();
        String sql = "SELECT * FROM bill_items";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BillItem billItem = new BillItem();
                billItem.setId(rs.getLong("id"));
                billItem.setQuantity(rs.getInt("quantity"));
                billItem.setUnitPrice(rs.getDouble("unit_price"));
                billItem.setSubTotal(rs.getDouble("sub_total"));

                Bill bill = new Bill();
                bill.setId(rs.getLong("bill_id"));
                billItem.setBill(bill);

                Items item = new Items();
                item.setId(rs.getLong("item_id"));
                billItem.setItem(item);

                list.add(billItem);
            }
        }
        return list;
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        String sql = "SELECT COUNT(*) FROM bill_items WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
