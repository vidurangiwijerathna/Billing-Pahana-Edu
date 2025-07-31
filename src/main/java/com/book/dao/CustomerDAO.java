package com.book.dao;

import com.book.dto.CustomerDTO;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean existsByAccountNumber(String accountNumber) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT id FROM customer WHERE account_number = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, accountNumber);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public CustomerDTO save(CustomerDTO dto) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO customer (account_number, name, address, telephone) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, dto.getAccountNumber());
        ps.setString(2, dto.getName());
        ps.setString(3, dto.getAddress());
        ps.setString(4, dto.getTelephone());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            dto.setId(rs.getLong(1));
        }
        return dto;
    }

    public List<CustomerDTO> findAll() throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<CustomerDTO> list = new ArrayList<>();
        while (rs.next()) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(rs.getLong("id"));
            dto.setAccountNumber(rs.getString("account_number"));
            dto.setName(rs.getString("name"));
            dto.setAddress(rs.getString("address"));
            dto.setTelephone(rs.getString("telephone"));
            list.add(dto);
        }
        return list;
    }

    public CustomerDTO findById(Long id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM customer WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(rs.getLong("id"));
            dto.setAccountNumber(rs.getString("account_number"));
            dto.setName(rs.getString("name"));
            dto.setAddress(rs.getString("address"));
            dto.setTelephone(rs.getString("telephone"));
            return dto;
        }
        return null;
    }

    public CustomerDTO update(CustomerDTO dto) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE customer SET account_number=?, name=?, address=?, telephone=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, dto.getAccountNumber());
        ps.setString(2, dto.getName());
        ps.setString(3, dto.getAddress());
        ps.setString(4, dto.getTelephone());
        ps.setLong(5, dto.getId());
        ps.executeUpdate();
        return dto;
    }

    public boolean delete(Long id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM customer WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps.executeUpdate() > 0;
    }

    public boolean existsById(Long id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT id FROM customer WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}
