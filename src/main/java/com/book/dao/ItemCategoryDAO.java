package com.book.dao;

import com.book.model.ItemCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ItemCategoryDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

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
        try {
            return em.createQuery("SELECT c FROM ItemCategory c ORDER BY c.itemId ASC", ItemCategory.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public String getLastItemId() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT c.itemId FROM ItemCategory c ORDER BY c.itemId DESC",
                    String.class
            );
            query.setMaxResults(1);
            List<String> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    /** ✅ Added for service usage */
    public ItemCategory findByItemId(String itemId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ItemCategory.class, itemId);
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

    public void delete(String itemId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ItemCategory category = em.find(ItemCategory.class, itemId);
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

    /** ✅ Added for service usage */
    public void deleteByItemId(String itemId) {
        delete(itemId); // reuse the delete method
    }
}
