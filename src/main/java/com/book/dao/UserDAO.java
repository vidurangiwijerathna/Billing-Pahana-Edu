package com.book.dao;

import com.book.model.User;
import jakarta.persistence.*;

import java.util.List;

public class UserDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public void save(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException("Failed to save user", e);
        } finally {
            em.close();
        }
    }

    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // User not found
        } finally {
            em.close();
        }
    }

}
