package com.book.dao;

import com.book.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ItemDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPU");

    public void save(Item item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }

    public Item findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Item item = em.find(Item.class, id);
        em.close();
        return item;
    }

    public List<Item> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Item> items = em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
        em.close();
        return items;
    }

    public void update(Item item) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.find(Item.class, id);
        if (item != null) {
            em.remove(item);
        }
        em.getTransaction().commit();
        em.close();
    }
}
