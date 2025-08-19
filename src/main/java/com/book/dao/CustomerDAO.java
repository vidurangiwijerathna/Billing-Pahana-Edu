package com.book.dao;

import com.book.dto.CustomerDTO;
import com.book.mapper.CustomerMapper;
import com.book.model.Customer;
import com.book.model.User;
import jakarta.persistence.*;

import java.util.List;

public class CustomerDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private CustomerMapper mapper = new CustomerMapper();

    public void save(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Failed to save customer", e);
        } finally {
            em.close();
        }
    }

    public List<Customer> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c ORDER BY c.id", Customer.class)
                    .getResultList();
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

    public Customer findById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    public void update(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Failed to update customer", e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) em.remove(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Failed to delete customer", e);
        } finally {
            em.close();
        }
    }

    public CustomerDTO findByName(String name) {
        EntityManager em = emf.createEntityManager();
        CustomerDTO customer = null;

        try {
            TypedQuery<Customer> query = em.createQuery(
                    "SELECT c FROM Customer c WHERE c.name = :name", Customer.class);
            query.setParameter("name", name);
            Customer result = query.getSingleResult();

            if (result != null) {
                customer = mapper.toDTO(result);
            }
        } catch (NoResultException e) {
            // no customer found, return null
        } finally {
            em.close();
        }
        return customer;
    }
}
