package com.book.dao;

import com.book.entity.Items;
import com.book.repository.ItemsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAO implements ItemsRepository {

    private Connection getConnection() throws SQLException {
        // Your DB connection logic
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "password");
    }

    @Override
    public Items save(Items item) throws Exception {
        String sql = "INSERT INTO items (name, author, price, stock, category_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getAuthor());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getStock());
            if (item.getCategory() != null) {
                stmt.setLong(5, item.getCategory().getId());
            } else {
                stmt.setNull(5, Types.BIGINT);
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(rs.getLong(1));
            }
        }
        return item;
    }

    @Override
    public Items findById(Long id) throws Exception {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Items item = new Items();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setAuthor(rs.getString("author"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                return item;
            }
        }
        return null;
    }

    @Override
    public List<Items> findAll() throws Exception {
        List<Items> list = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Items item = new Items();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setAuthor(rs.getString("author"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public boolean update(Items item) throws Exception {
        String sql = "UPDATE items SET name=?, author=?, price=?, stock=?, category_id=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getAuthor());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getStock());
            if (item.getCategory() != null) {
                stmt.setLong(5, item.getCategory().getId());
            } else {
                stmt.setNull(5, Types.BIGINT);
            }
            stmt.setLong(6, item.getId());
            return stmt.executeUpdate() > 0; // ✅ return boolean
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        String sql = "DELETE FROM items WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0; // ✅ return boolean
        }
    }
}
