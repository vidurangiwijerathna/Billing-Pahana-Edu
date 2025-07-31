package com.book.repository.impl;

import com.book.entity.Bill;
import com.book.entity.Customer;
import com.book.repository.BillRepository;
import com.book.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryImpl implements BillRepository {

    @Override
    public Bill save(Bill bill) throws Exception {
        String sql = "INSERT INTO bill (customer_id, total, created_at) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, bill.getCustomer().getId());
            stmt.setDouble(2, bill.getTotal());
            stmt.setTimestamp(3, Timestamp.valueOf(bill.getCreatedAt()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bill.setId(rs.getLong(1));
                }
            }
        }
        return bill;
    }

    @Override
    public Bill update(Bill bill) throws Exception {
        String sql = "UPDATE bill SET customer_id=?, total=?, created_at=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, bill.getCustomer().getId());
            stmt.setDouble(2, bill.getTotal());
            stmt.setTimestamp(3, Timestamp.valueOf(bill.getCreatedAt()));
            stmt.setLong(4, bill.getId());

            stmt.executeUpdate();
        }
        return bill;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        String sql = "DELETE FROM bill WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Bill findById(Long id) throws Exception {
        String sql = "SELECT * FROM bill WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bill bill = new Bill();
                    bill.setId(rs.getLong("id"));

                    // Load customer reference (only id for now, can be expanded)
                    Customer customer = new Customer();
                    customer.setId(rs.getLong("customer_id"));
                    bill.setCustomer(customer);

                    bill.setTotal(rs.getDouble("total"));

                    Timestamp ts = rs.getTimestamp("created_at");
                    if (ts != null) {
                        bill.setCreatedAt(ts.toLocalDateTime());
                    }

                    // You can optionally load BillItems here if needed

                    return bill;
                }
            }
        }
        return null;
    }

    @Override
    public List<Bill> findAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getLong("id"));

                Customer customer = new Customer();
                customer.setId(rs.getLong("customer_id"));
                bill.setCustomer(customer);

                bill.setTotal(rs.getDouble("total"));

                Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    bill.setCreatedAt(ts.toLocalDateTime());
                }

                bills.add(bill);
            }
        }
        return bills;
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        String sql = "SELECT COUNT(*) FROM bill WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
