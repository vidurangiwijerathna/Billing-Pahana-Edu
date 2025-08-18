package com.book.dao;

import com.book.dto.BillDTO;
import com.book.dto.BillItemDTO;
import com.book.model.Bill;
import com.book.model.BillItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Insert a new Bill along with its BillItems
    public Integer insert(BillDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Create Bill entity
            Bill bill = new Bill();
            bill.setCustomerId(dto.getCustomerId());
            bill.setCreatedBy(dto.getCreatedBy());
            bill.setTotalAmount(dto.getTotalAmount());

            // Add BillItems
            if (dto.getBillItems() != null) {
                List<BillItem> items = new ArrayList<>();
                for (BillItemDTO itemDTO : dto.getBillItems()) {
                    BillItem item = new BillItem();
                    item.setBill(bill); // link to Bill
                    item.setItemId(itemDTO.getItemId());
                    item.setQuantity(itemDTO.getQuantity());
                    item.setPrice(itemDTO.getPrice());
                    items.add(item);
                }
                bill.setBillItems(items);
            }

            em.persist(bill); // persist bill + cascade will persist items
            em.getTransaction().commit();

            return bill.getId();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Failed to insert bill", e);
        } finally {
            em.close();
        }
    }

    // Return all bills
    public List<BillDTO> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Bill> bills = em.createQuery("SELECT b FROM Bill b ORDER BY b.id ASC", Bill.class)
                    .getResultList();
            List<BillDTO> result = new ArrayList<>();
            for (Bill b : bills) {
                BillDTO dto = new BillDTO();
                dto.setId(b.getId());
                dto.setCustomerId(b.getCustomerId());
                dto.setCreatedBy(b.getCreatedBy());
                dto.setTotalAmount(b.getTotalAmount());

                List<BillItemDTO> items = new ArrayList<>();
                if (b.getBillItems() != null) {
                    for (BillItem bi : b.getBillItems()) {
                        BillItemDTO bidto = new BillItemDTO();
                        bidto.setId(bi.getId());
                        bidto.setItemId(bi.getItemId());
                        bidto.setQuantity(bi.getQuantity());
                        bidto.setPrice(bi.getPrice());
                        items.add(bidto);
                    }
                }
                dto.setBillItems(items);
                result.add(dto);
            }
            return result;
        } finally {
            em.close();
        }
    }

    // Find bill by ID
    public BillDTO findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            Bill b = em.find(Bill.class, id);
            if (b == null) return null;

            BillDTO dto = new BillDTO();
            dto.setId(b.getId());
            dto.setCustomerId(b.getCustomerId());
            dto.setCreatedBy(b.getCreatedBy());
            dto.setTotalAmount(b.getTotalAmount());

            List<BillItemDTO> items = new ArrayList<>();
            if (b.getBillItems() != null) {
                for (BillItem bi : b.getBillItems()) {
                    BillItemDTO bidto = new BillItemDTO();
                    bidto.setId(bi.getId());
                    bidto.setItemId(bi.getItemId());
                    bidto.setQuantity(bi.getQuantity());
                    bidto.setPrice(bi.getPrice());
                    items.add(bidto);
                }
            }
            dto.setBillItems(items);
            return dto;
        } finally {
            em.close();
        }
    }

    // Update bill
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

    // Delete bill + its items
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Bill bill = em.find(Bill.class, id);
            if (bill != null) {
                em.remove(bill); // cascade will remove bill items
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
