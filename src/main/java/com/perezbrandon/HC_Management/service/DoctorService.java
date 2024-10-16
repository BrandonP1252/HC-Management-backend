package com.perezbrandon.HC_Management.service;

import com.perezbrandon.HC_Management.dto.DoctorRegReq;
import com.perezbrandon.HC_Management.model.Doctor;
import com.perezbrandon.HC_Management.model.Role;
import com.perezbrandon.HC_Management.respository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // registers doctor
    public Doctor registerDoctor(DoctorRegReq doctor) {
        Optional<Doctor> doctorByUsername = doctorRepository.doctorByUsername(doctor.getUsername());
        Optional<Doctor> doctorByEmail = doctorRepository.doctorByEmail(doctor.getEmail());

        // checks if username exists
        if (doctorByUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // checks if email exist
        if (doctorByEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // converts DoctorRegReq to Doctor
        Doctor newDoctor = convertToEntity(doctor);
        doctorRepository.save(newDoctor);
        return newDoctor;

    }

    // DTO conversion
    public Doctor convertToEntity(DoctorRegReq doctor) {
        Doctor newDoctor = new Doctor();
        newDoctor.setUsername(doctor.getUsername());
        newDoctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        newDoctor.setName(doctor.getName());
        newDoctor.setEmail(doctor.getEmail());
        newDoctor.setSpecialty(doctor.getSpecialty());
        newDoctor.setPhone(doctor.getPhone());
        newDoctor.setRole(Role.DOCTOR);
        return newDoctor;
    }

    // Get Doctors
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    // Delete doctor
    public void deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }


}
