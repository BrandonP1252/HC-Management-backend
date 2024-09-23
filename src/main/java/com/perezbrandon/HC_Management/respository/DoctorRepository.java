package com.perezbrandon.HC_Management.respository;

import com.perezbrandon.HC_Management.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Finds doctor by username
    @Query("SELECT d FROM Doctor d WHERE d.username = :username")
    Optional<Doctor> doctorByUsername(String username);

    // Finds doctor by email
    @Query("SELECT d FROM Doctor d WHERE d.email = :email")
    Optional<Doctor> doctorByEmail(String email);
}
