package com.perezbrandon.HC_Management.service;

import com.perezbrandon.HC_Management.model.Admin;
import com.perezbrandon.HC_Management.model.Doctor;
import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.respository.AdminRepository;
import com.perezbrandon.HC_Management.respository.DoctorRepository;
import com.perezbrandon.HC_Management.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(PatientRepository patientRepository, DoctorRepository doctorRepository, AdminRepository adminRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Checks if username is a doctor
        Optional<Doctor> optional_doctor = doctorRepository.doctorByUsername(username);
        if (optional_doctor.isPresent()) {
            Doctor doctor = optional_doctor.get();
            return User.builder()
                    .username(doctor.getUsername())
                    .password(doctor.getPassword())
                    .roles(doctor.getRole().name())
                    .build();
        }

        // Checks if username is a patient
        Optional<Patient> optional_patient = patientRepository.patientByUsername(username);
        if (optional_patient.isPresent()) {
            Patient patient = optional_patient.get();
            return User.builder()
                    .username(patient.getUsername())
                    .password(patient.getPassword())
                    .roles(patient.getRole().name())
                    .build();
        }

        // Checks if username is an admin
        Optional<Admin> optional_admin = adminRepository.adminByUsername(username);
        if (optional_admin.isPresent()) {
            Admin admin = optional_admin.get();
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles(admin.getRole().name())
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
