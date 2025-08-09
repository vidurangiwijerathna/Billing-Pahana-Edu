package com.book.service;

import com.book.entity.Users;

import java.sql.SQLException;
import java.util.List;

// Service interface
public interface UsersService {
    Users getUserByEmail(String email) throws SQLException;
    Users getUserById(int id) throws SQLException;
    List<Users> getAllUsers() throws SQLException;
    boolean registerUser(Users user) throws SQLException;
    boolean updateUser(Users user) throws SQLException;
    boolean deleteUser(int id) throws SQLException;
}

// Service implementation
class UsersServiceImpl implements UsersService {

    private com.book.repository.UsersRepo usersRepo = new com.book.repository.UsersRepo();

    @Override
    public Users getUserByEmail(String email) throws SQLException {
        return usersRepo.findByEmail(email);
    }

    @Override
    public Users getUserById(int id) throws SQLException {
        return usersRepo.findById(id);
    }

    @Override
    public List<Users> getAllUsers() throws SQLException {
        return usersRepo.findAll();
    }

    @Override
    public boolean registerUser(Users user) throws SQLException {
        return usersRepo.save(user);
    }

    @Override
    public boolean updateUser(Users user) throws SQLException {
        return usersRepo.update(user);
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return usersRepo.delete(id);
    }
}
