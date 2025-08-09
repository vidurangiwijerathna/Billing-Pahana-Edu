package com.book.dao;

import com.book.entity.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class BillDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit-name");

    public void save(Bill bill) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bill);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Bill findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    public List<Bill> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Bill b", Bill.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Bill bill) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(bill);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Bill bill = em.find(Bill.class, id);
            if (bill != null) {
                em.remove(bill);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
