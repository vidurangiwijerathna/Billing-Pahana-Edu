package com.book.dto;

import com.book.entity.Roles;

public record TokenDTO(String token, Long id,  String email, String name, String address, String tele, Roles role) {
}
