package com.book.repository;

import com.book.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsersRepo {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pahanaedu");

    public Users findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class);
            query.setParameter("email", email);
            List<Users> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public Users findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public List<Users> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Users save(Users user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } finally {
            em.close();
        }
    }

    public Users update(Users user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Users updatedUser = em.merge(user);
            em.getTransaction().commit();
            return updatedUser;
        } finally {
            em.close();
        }
    }

    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Users user = em.find(Users.class, id);
            if (user != null) {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } finally {
            em.close();
        }
    }
}
