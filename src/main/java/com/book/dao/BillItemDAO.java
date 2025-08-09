package com.book.dao;

import com.book.entity.BillItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class BillItemDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("your-persistence-unit-name");

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

    public BillItem findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(BillItem.class, id);
        } finally {
            em.close();
        }
    }

    public List<BillItem> findByBillId(Long billId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT bi FROM BillItem bi WHERE bi.bill.id = :billId", BillItem.class)
                    .setParameter("billId", billId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void update(BillItem billItem) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(billItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            BillItem billItem = em.find(BillItem.class, id);
            if (billItem != null) {
                em.remove(billItem);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
