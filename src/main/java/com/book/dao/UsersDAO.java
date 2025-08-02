package com.book.dao;

import com.book.dto.UsersDTO;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    // Save new user (Admin or Cashier)
    public boolean createUser(UsersDTO user) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    // Get all users (for admin view)
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
            dto.setPassword(rs.getString("password")); // added
            dto.setRole(rs.getString("role"));
            users.add(dto);
        }
        return users;
    }

    //  Used in LoginServlet
    public UsersDTO getUserByEmail(String email) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password")); // added
            user.setRole(rs.getString("role"));
            return user;
        }
        return null;
    }

    //  Update existing user
    public boolean updateUser(UsersDTO user) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.setInt(5, user.getId());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    // Delete user by ID
    public boolean deleteUser(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        return rows > 0;
    }
}
