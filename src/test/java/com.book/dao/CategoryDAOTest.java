package com.book.dao;

import com.book.model.ItemCategory;
import org.junit.jupiter.api.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryDAOTest {

    private static ItemCategoryDAO categoryDAO;
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setup() {
        // Use test persistence unit with H2 (configure in persistence.xml as myPU-test)
        emf = Persistence.createEntityManagerFactory("myPU");
        categoryDAO = new ItemCategoryDAO();
    }

    @AfterAll
    static void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }



    @Test
    @Order(1)
    void testFindById() {
        List<ItemCategory> categories = categoryDAO.findAll();
        assertFalse(categories.isEmpty());

        ItemCategory saved = categories.get(0);
        ItemCategory found = categoryDAO.findById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getCategoryName(), found.getCategoryName());
    }

    @Test
    @Order(2)
    void testUpdateCategory() {
        List<ItemCategory> categories = categoryDAO.findAll();
        ItemCategory category = categories.get(0);

        category.setCategoryName("Updated Fiction");
        categoryDAO.update(category);

        ItemCategory updated = categoryDAO.findById(category.getId());
        assertEquals("Updated Fiction", updated.getCategoryName());
    }


}
