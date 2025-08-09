package com.book.service.impl;

import com.book.entity.Users;
import com.book.repository.UsersRepo;
import com.book.service.UsersService;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepo = new UsersRepo();

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
        return usersRepo.save(user) != null;
    }

    @Override
    public boolean updateUser(Users user) throws SQLException {
        return usersRepo.update(user) != null;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return usersRepo.delete(id);
    }
}
