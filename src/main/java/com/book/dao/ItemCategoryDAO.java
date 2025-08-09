package com.book.dao;

import com.book.model.ItemCategory;
import jakarta.persistence.*;

import java.util.List;

public class ItemCategoryDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (category.getId() == null) {
                em.persist(category);
            } else {
                em.merge(category);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<ItemCategory> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM ItemCategory c", ItemCategory.class).getResultList();
        } finally {
            em.close();
        }
    }

    public ItemCategory findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ItemCategory.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ItemCategory category = em.find(ItemCategory.class, id);
            if (category != null) {
                em.remove(category);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
