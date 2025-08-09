package com.book.repository.impl;

import com.book.entity.ItemCategory;
import com.book.repository.ItemCategoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ItemCategoryRepositoryImpl implements ItemCategoryRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshopPU");

    @Override
    public ItemCategory save(ItemCategory category) {
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
        return category;
    }

    @Override
    public ItemCategory findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ItemCategory.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<ItemCategory> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ItemCategory> query = em.createQuery("SELECT ic FROM ItemCategory ic", ItemCategory.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(ItemCategory category) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            ItemCategory category = em.find(ItemCategory.class, id);
            if (category != null) {
                em.getTransaction().begin();
                em.remove(category);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
