package com.perezbrandon.HC_Management.service;

import com.perezbrandon.HC_Management.dto.AdminRegReq;
import com.perezbrandon.HC_Management.model.Admin;
import com.perezbrandon.HC_Management.model.Role;
import com.perezbrandon.HC_Management.respository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin registerAdmin(AdminRegReq admin) {
        Optional<Admin> adminByUsername = adminRepository.adminByUsername(admin.getUsername());
        Optional<Admin> adminByEmail = adminRepository.adminByEmail(admin.getEmail());

        if (adminByUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (adminByEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Admin newAdmin = convertToEntity(admin);
        adminRepository.save(newAdmin);
        return newAdmin;

    }

    public Admin convertToEntity(AdminRegReq admin) {
        Admin newAdmin = new Admin();
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setName(admin.getName());
        newAdmin.setUsername(admin.getUsername());
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        newAdmin.setPhone(admin.getPhone());
        newAdmin.setRole(Role.ADMIN);
        return newAdmin;
    }


}
