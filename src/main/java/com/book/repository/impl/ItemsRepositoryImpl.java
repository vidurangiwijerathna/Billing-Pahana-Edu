package com.book.repository.impl;

import com.book.entity.Items;
import com.book.entity.ItemCategory;
import com.book.repository.ItemsRepository;
import com.book.repository.impl.ItemCategoryRepositoryImpl;
import com.book.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsRepositoryImpl implements ItemsRepository {

    private ItemCategoryRepositoryImpl categoryRepo = new ItemCategoryRepositoryImpl();

    @Override
    public Items save(Items item) throws Exception {
        String sql = "INSERT INTO items (name, author, price, stock, category_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getAuthor());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getStock());

            if (item.getCategory() != null && item.getCategory().getId() != null) {
                ps.setLong(5, item.getCategory().getId());
            } else {
                ps.setNull(5, Types.BIGINT);
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating item failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating item failed, no ID obtained.");
                }
            }
        }
        return item;
    }

    @Override
    public Items findById(Long id) throws Exception {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Items item = new Items();
                    item.setId(rs.getLong("id"));
                    item.setName(rs.getString("name"));
                    item.setAuthor(rs.getString("author"));
                    item.setPrice(rs.getDouble("price"));
                    item.setStock(rs.getInt("stock"));

                    Long categoryId = rs.getLong("category_id");
                    if (categoryId != null && categoryId != 0) {
                        ItemCategory category = categoryRepo.findById(categoryId);
                        item.setCategory(category);
                    }
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public List<Items> findAll() throws Exception {
        List<Items> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Items item = new Items();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setAuthor(rs.getString("author"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));

                Long categoryId = rs.getLong("category_id");
                if (categoryId != null && categoryId != 0) {
                    ItemCategory category = categoryRepo.findById(categoryId);
                    item.setCategory(category);
                }
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public boolean update(Items item) throws Exception {
        String sql = "UPDATE items SET name = ?, author = ?, price = ?, stock = ?, category_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getAuthor());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getStock());

            if (item.getCategory() != null && item.getCategory().getId() != null) {
                ps.setLong(5, item.getCategory().getId());
            } else {
                ps.setNull(5, Types.BIGINT);
            }

            ps.setLong(6, item.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
