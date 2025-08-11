package com.book.dao;

import com.book.model.Item;
import jakarta.persistence.*;

import java.util.List;

public class ItemDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(Item item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Item> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM Item i ORDER BY i.id ASC", Item.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Item findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public void update(Item item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Item item = em.find(Item.class, id);
            if (item != null) em.remove(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
