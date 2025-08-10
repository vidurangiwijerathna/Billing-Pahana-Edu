package com.book.service;

import com.book.dao.UserDAO;
import com.book.dto.UserDTO;
import com.book.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(String name, String email, String password, String role) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole(role);
        userDAO.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User authenticateUser(String email, String plainPassword) {
        User user = userDAO.findByEmail(email);
        if (user != null && BCrypt.checkpw(plainPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> viewAllUsers() {
        return userDAO.findAll();
    }

    // New method to fetch all users as UserDTO list
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.findAll();

        return users.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());  // Note: Usually avoid exposing passwords
            dto.setRole(user.getRole());
            return dto;
        }).collect(Collectors.toList());
    }
}
