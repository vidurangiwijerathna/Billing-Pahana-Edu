package com.book.repository.impl;

import com.book.entity.Customer;
import com.book.repository.CustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static final EntityManager em = Persistence
            .createEntityManagerFactory("bookshopPU")
            .createEntityManager();

    @Override
    public Customer save(Customer customer) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(customer);
            tx.commit();
            return customer;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Customer update(Customer customer) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            customer = em.merge(customer);
            tx.commit();
            return customer;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Customer findById(Long id) throws Exception {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() throws Exception {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public boolean existsById(Long id) throws Exception {
        Customer customer = em.find(Customer.class, id);
        return customer != null;
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) throws Exception {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Customer c WHERE c.accountNumber = :accountNumber", Long.class);
        query.setParameter("accountNumber", accountNumber);
        Long count = query.getSingleResult();
        return count > 0;
    }
}
