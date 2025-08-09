package com.book.repository.impl;

import com.book.entity.Items;
import com.book.entity.ItemCategory;
import com.book.repository.ItemsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ItemsRepositoryImpl implements ItemsRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pahanaedu");

    private ItemCategoryRepositoryImpl categoryRepo = new ItemCategoryRepositoryImpl();

    @Override
    public Items save(Items item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (item.getCategory() != null && item.getCategory().getId() != null) {
                ItemCategory category = categoryRepo.findById(item.getCategory().getId());
                item.setCategory(category);
            }
            em.persist(item);
            em.getTransaction().commit();
            return item;
        } finally {
            em.close();
        }
    }

    @Override
    public Items findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Items.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Items> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM Items i", Items.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(Items item) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (item.getCategory() != null && item.getCategory().getId() != null) {
                ItemCategory category = categoryRepo.findById(item.getCategory().getId());
                item.setCategory(category);
            }
            em.merge(item);
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
            Items item = em.find(Items.class, id);
            if (item != null) {
                em.getTransaction().begin();
                em.remove(item);
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
