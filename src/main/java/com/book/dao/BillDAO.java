package com.book.dao;

import com.book.model.Bill;
import com.book.model.BillItem;
import jakarta.persistence.*;

import java.util.List;

public class BillDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(Bill bill) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            // Persist the bill first
            em.persist(bill);

            // Persist all bill items
            if (bill.getBillItems() != null) {
                for (BillItem item : bill.getBillItems()) {
                    item.setBill(bill); // link bill to item
                    em.persist(item);
                }
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Bill> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT b FROM Bill b ORDER BY b.id ASC", Bill.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Bill findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Bill.class, id);
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

    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Bill bill = em.find(Bill.class, id);
            if (bill != null) {
                // Delete bill items first
                em.createQuery("DELETE FROM BillItem bi WHERE bi.bill.id = :billId")
                        .setParameter("billId", bill.getId())
                        .executeUpdate();
                // Then delete the bill
                em.remove(bill);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
