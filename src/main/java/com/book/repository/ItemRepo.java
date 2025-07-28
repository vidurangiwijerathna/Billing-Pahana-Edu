package com.book.repository;

import com.book.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Items, Long> {
}
