package com.perezbrandon.HC_Management.service;

import com.perezbrandon.HC_Management.dto.PatientRegReq;
import com.perezbrandon.HC_Management.model.Gender;
import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.model.Role;
import com.perezbrandon.HC_Management.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // registers patient
    public Patient registerPatient(PatientRegReq patient) {
        Optional<Patient> patientByUsername = patientRepository.patientByUsername(patient.getUsername());
        Optional<Patient> patientByEmail = patientRepository.patientByEmail(patient.getEmail());

        // check if username exists
        if (patientByUsername.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        // check if email exists
        if (patientByEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        // convert PatientRegReq to Patient
        Patient newPatient = convertToEntity(patient);
        patientRepository.save(newPatient);
        return newPatient;

    }

    // DTO conversion
    public Patient convertToEntity(PatientRegReq patient) {
        Patient newPatient = new Patient();
        newPatient.setUsername(patient.getUsername());
        newPatient.setPassword(passwordEncoder.encode(patient.getPassword()));
        newPatient.setName(patient.getName());
        newPatient.setEmail(patient.getEmail());
        newPatient.setAddress(patient.getAddress());
        newPatient.setPhone(patient.getPhone());
        LocalDate date = LocalDate.parse(patient.getDate_of_birth());
        newPatient.setDate_of_birth(Date.valueOf(date));
        newPatient.setGender(Gender.valueOf(patient.getGender().toUpperCase()));
        newPatient.setRole(Role.PATIENT);
        return newPatient;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }


}
