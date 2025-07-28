package com.book.dao;


import com.book.entity.Users;
import com.book.repository.UserRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersDAO {

    private final UserRepo userRepo;

    //constructor parameter
    public UsersDAO(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users createUsers(Users admin){
        return userRepo.save(admin);
    }

    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    public List<Users> getAllUsersByEmail(String email){
        return userRepo.findByEmail(email);
    }
}

