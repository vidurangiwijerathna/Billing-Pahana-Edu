package com.book.dao;

import com.book.model.Item;
import com.book.model.ItemCategory;
import org.junit.jupiter.api.*;

import jakarta.persistence.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemDAOTest {

    private EntityManagerFactory emf;
    private ItemDAO itemDAO;

    @BeforeAll
    void setupEntityManagerFactory() {
        // Use your persistence unit from persistence.xml
        emf = Persistence.createEntityManagerFactory("S");
        itemDAO = new ItemDAO();
    }

    @AfterAll
    void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    private ItemCategory createCategory(EntityManager em) {
        ItemCategory category = new ItemCategory();
        category.setCategoryName("Test Category");

        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();

        return category;
    }

    @Test
    void testSaveAndFindById() {
        EntityManager em = emf.createEntityManager();
        ItemCategory category = createCategory(em);

        Item item = new Item();
        item.setName("Test Book");
        item.setAuthor("Author A");
        item.setPrice(100.0);
        item.setStock(10);
        item.setCategory(category);

        itemDAO.save(item);

        assertNotNull(item.getId(), "Item ID should not be null after saving");

        Item found = itemDAO.findById(item.getId());
        assertNotNull(found, "Item should be found by ID");
        assertEquals("Test Book", found.getName());
        em.close();
    }

    @Test
    void testUpdate() {
        EntityManager em = emf.createEntityManager();
        ItemCategory category = createCategory(em);

        Item item = new Item();
        item.setName("Old Book");
        item.setAuthor("Author B");
        item.setPrice(200.0);
        item.setStock(5);
        item.setCategory(category);
        itemDAO.save(item);

        item.setName("Updated Book");
        item.setPrice(250.0);
        itemDAO.update(item);

        Item updated = itemDAO.findById(item.getId());
        assertEquals("Updated Book", updated.getName());
        assertEquals(250.0, updated.getPrice());
        em.close();
    }

    @Test
    void testDelete() {
        EntityManager em = emf.createEntityManager();
        ItemCategory category = createCategory(em);

        Item item = new Item();
        item.setName("Delete Book");
        item.setAuthor("Author C");
        item.setPrice(50.0);
        item.setStock(3);
        item.setCategory(category);
        itemDAO.save(item);

        Long id = item.getId();
        itemDAO.delete(id);

        Item deleted = itemDAO.findById(id);
        assertNull(deleted, "Item should be null after deletion");
        em.close();
    }

    @Test
    void testGetAllAndFindAll() {
        List<Item> allItems = itemDAO.getAll();
        List<Item> findAllItems = itemDAO.findAll();

        assertNotNull(allItems);
        assertNotNull(findAllItems);
    }

    @Test
    void testGetUnitPrice() {
        EntityManager em = emf.createEntityManager();
        ItemCategory category = createCategory(em);

        Item item = new Item();
        item.setName("Price Test Book");
        item.setAuthor("Author D");
        item.setPrice(500.0);
        item.setStock(2);
        item.setCategory(category);
        itemDAO.save(item);

        double price = itemDAO.getUnitPrice(item.getId());
        assertEquals(500.0, price, "Unit price should match saved value");
        em.close();
    }
}
