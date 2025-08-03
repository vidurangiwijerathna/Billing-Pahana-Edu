package com.book.repository;

import com.book.entity.ItemCategory;
import java.sql.SQLException;
import java.util.List;

public interface ItemCategoryRepository {

    ItemCategory save(ItemCategory category) throws SQLException;

    ItemCategory findById(Long id) throws SQLException;

    List<ItemCategory> findAll() throws SQLException;

    boolean update(ItemCategory category) throws SQLException;

    boolean delete(Long id) throws SQLException;
}
