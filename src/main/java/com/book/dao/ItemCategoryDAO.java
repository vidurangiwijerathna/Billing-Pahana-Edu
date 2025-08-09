package com.book.dao;

import com.book.entity.ItemCategory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ItemCategoryDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public ItemCategory save(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            return category;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
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

    public List<ItemCategory> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ItemCategory> query = em.createQuery("SELECT c FROM ItemCategory c", ItemCategory.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public ItemCategory update(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemCategory updated = em.merge(category);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemCategory category = em.find(ItemCategory.class, id);
            if (category != null) {
                em.remove(category);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
