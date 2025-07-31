package com.book.dao;

import com.book.dto.UsersDTO;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public boolean createUser(UsersDTO user) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO users (username, email, role) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getRole());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public List<UsersDTO> getAllUsers() throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<UsersDTO> users = new ArrayList<>();
        while (rs.next()) {
            UsersDTO dto = new UsersDTO();
            dto.setId(rs.getInt("id"));
            dto.setUsername(rs.getString("username"));
            dto.setEmail(rs.getString("email"));
            dto.setRole(rs.getString("role"));
            users.add(dto);
        }
        return users;
    }

    public UsersDTO getUserByEmail(String email) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new UsersDTO(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("role")
            );
        }
        return null;
    }
}
