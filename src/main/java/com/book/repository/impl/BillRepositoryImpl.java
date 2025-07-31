package com.book.repository.impl;

import com.book.entity.Bill;
import com.book.repository.BillRepository;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryImpl implements BillRepository {

    @Override
    public void save(Bill bill) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO bill (customer_id, created_at) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, bill.getCustomerId());
        ps.setTimestamp(2, new Timestamp(bill.getCreatedAt().getTime()));
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            bill.setId(rs.getInt(1));
        }

        rs.close();
        ps.close();
        conn.close();
    }

    @Override
    public Bill findById(int id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM bill WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Bill bill = null;
        if (rs.next()) {
            bill = new Bill();
            bill.setId(rs.getInt("id"));
            bill.setCustomerId(rs.getInt("customer_id"));
            bill.setCreatedAt(rs.getTimestamp("created_at"));
        }

        rs.close();
        ps.close();
        conn.close();
        return bill;
    }

    @Override
    public List<Bill> findAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM bill";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bill bill = new Bill();
            bill.setId(rs.getInt("id"));
            bill.setCustomerId(rs.getInt("customer_id"));
            bill.setCreatedAt(rs.getTimestamp("created_at"));
            bills.add(bill);
        }

        rs.close();
        ps.close();
        conn.close();
        return bills;
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT 1 FROM bill WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        boolean exists = rs.next();

        rs.close();
        ps.close();
        conn.close();
        return exists;
    }
}
