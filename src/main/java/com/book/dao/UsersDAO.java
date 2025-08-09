package com.book.dao;

import com.book.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UsersDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshop");

    public boolean save(Users user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Users findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }
}
