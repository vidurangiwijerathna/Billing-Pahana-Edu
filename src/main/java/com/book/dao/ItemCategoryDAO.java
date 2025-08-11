package com.book.dao;

import com.book.model.ItemCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ItemCategoryDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<ItemCategory> getAll() {
        EntityManager em = emf.createEntityManager();

            return em.createQuery("SELECT c FROM ItemCategory c", ItemCategory.class)
                    .getResultList();

    }

    public ItemCategory findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ItemCategory.class, id);
        } finally {
            em.close();
        }
    }

    public void update(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemCategory category = em.find(ItemCategory.class, id);
            if (category != null) {
                em.remove(category);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
