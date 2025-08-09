package com.book.repository.impl;

import com.book.entity.Bill;
import com.book.repository.BillRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BillRepositoryImpl implements BillRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    @Override
    public void save(Bill bill) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bill);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Bill findById(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Bill> findAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Bill> query = em.createQuery("SELECT b FROM Bill b", Bill.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Bill bill = em.find(Bill.class, id.intValue());
            return bill != null;
        } finally {
            em.close();
        }
    }
}
