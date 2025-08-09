package com.book.repository;

import com.book.entity.Items;
import java.util.List;

public interface ItemsRepository {

    Items save(Items item) throws Exception;

    Items findById(Long id) throws Exception;

    List<Items> findAll() throws Exception;

    boolean update(Items item) throws Exception;

    boolean delete(Long id) throws Exception;
}
