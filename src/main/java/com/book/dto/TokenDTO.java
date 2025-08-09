package com.book.dto;

import com.book.entity.Roles;

public class TokenDTO {
    private String token;
    private Long id;
    private String email;
    private String name;
    private String address;
    private String tele;
    private Roles role;

    public TokenDTO() {}

    public TokenDTO(String token, Long id, String email, String name, String address, String tele, Roles role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.tele = tele;
        this.role = role;
    }

    // Getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }

    public Roles getRole() { return role; }
    public void setRole(Roles role) { this.role = role; }
}
