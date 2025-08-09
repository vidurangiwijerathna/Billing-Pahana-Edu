package com.book.dto.requestDTO;

import com.book.entity.Roles;

public class RegistrationOrLoginAdminsDTO {
    private String email;
    private String name;
    private String address;
    private String tele;
    private String password;
    private Roles role;

    public RegistrationOrLoginAdminsDTO() {}

    public RegistrationOrLoginAdminsDTO(String email, String name, String address, String tele, String password, Roles role) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.tele = tele;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Roles getRole() { return role; }
    public void setRole(Roles role) { this.role = role; }
}
