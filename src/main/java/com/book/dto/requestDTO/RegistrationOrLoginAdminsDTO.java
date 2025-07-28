package com.book.dto.requestDTO;

import com.book.entity.Roles;

public record RegistrationOrLoginAdminsDTO(String email, String name, String address, String tele, String password, Roles role) {
}
