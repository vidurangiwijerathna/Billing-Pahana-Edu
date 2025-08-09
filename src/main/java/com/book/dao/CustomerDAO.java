package com.book.dao;

import com.book.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshop");

    public boolean existsByAccountNumber(String accountNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM Customer c WHERE c.accountNumber = :accountNumber", Long.class);
            query.setParameter("accountNumber", accountNumber);
            Long count = query.getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public Customer save(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Customer> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public Customer update(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer updatedCustomer = em.merge(customer);
            em.getTransaction().commit();
            return updatedCustomer;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
                em.getTransaction().commit();
                return true;
            }
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean existsById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM Customer c WHERE c.id = :id", Long.class);
            query.setParameter("id", id);
            Long count = query.getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }
}
