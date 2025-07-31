package com.book.mapper;

import com.book.entity.Users;
import com.book.dto.UsersDTO;

public class UsersMapper {

    public static UsersDTO toDTO(Users user) {
        if (user == null) return null;
        return new UsersDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public static Users toEntity(UsersDTO dto) {
        if (dto == null) return null;
        Users user = new Users();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // Password not included in DTO for security reasons; set separately if needed
        user.setRole(dto.getRole());
        return user;
    }
}
