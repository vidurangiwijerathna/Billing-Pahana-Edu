package com.book.repository.impl;

import com.book.entity.BillItem;
import com.book.repository.BillItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BillItemRepositoryImpl implements BillItemRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshopPU");

    @Override
    public BillItem save(BillItem billItem) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(billItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return billItem;
    }

    @Override
    public BillItem update(BillItem billItem) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            billItem = em.merge(billItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return billItem;
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            BillItem billItem = em.find(BillItem.class, id);
            if (billItem != null) {
                em.getTransaction().begin();
                em.remove(billItem);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<BillItem> findByBillId(int billId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BillItem> query = em.createQuery(
                    "SELECT b FROM BillItem b WHERE b.billId = :billId", BillItem.class);
            query.setParameter("billId", billId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
