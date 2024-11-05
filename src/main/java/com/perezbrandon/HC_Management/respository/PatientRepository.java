package com.perezbrandon.HC_Management.respository;

import com.perezbrandon.HC_Management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // Finds patient by username
    @Query("SELECT p FROM Patient p WHERE p.username = :username")
    Optional<Patient> patientByUsername(String username);

    // Finds patient by email
    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    Optional<Patient> patientByEmail(String email);

    // Finds patient by username and password
    @Query("SELECT p FROM Patient p WHERE p.username = :username AND p.password = :password")
    Optional<Patient> patientByLogin(String username, String password);

    

}
