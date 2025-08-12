package com.book.dao;

import com.book.model.BillItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class BillItemDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(BillItem billItem) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(billItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<BillItem> getByBillId(Integer billId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT bi FROM BillItem bi WHERE bi.bill.id = :billId", BillItem.class)
                    .setParameter("billId", billId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteByBillId(Integer billId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM BillItem bi WHERE bi.bill.id = :billId")
                    .setParameter("billId", billId)
                    .executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
