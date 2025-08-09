package com.book.mapper;

import com.book.dto.UserDTO;
import com.book.model.User;

public class UserMapper {

    // Convert User entity to UserDTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        // Do NOT set password in DTO for security reasons, unless necessary
        dto.setRole(user.getRole());
        return dto;
    }

    // Convert UserDTO to User entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        // Password should be set separately (e.g., hashed password), so skip here or handle carefully
        user.setRole(dto.getRole());
        return user;
    }
}
