package com.perezbrandon.HC_Management.dto;

public class DoctorRegReq {
    private String username;
    private String password;
    private String name;
    private String specialty;
    private String email;
    private String phone;

    public DoctorRegReq(String username, String password, String name, String specialty, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
