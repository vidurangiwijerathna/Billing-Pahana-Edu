package com.book.repository;

import com.book.entity.Items;
import java.sql.SQLException;
import java.util.List;

public interface ItemsRepository {
    Items save(Items item) throws SQLException;
    Items findById(Long id) throws SQLException;
    List<Items> findAll() throws SQLException;
    boolean update(Items item) throws SQLException;
    boolean delete(Long id) throws SQLException;
}
