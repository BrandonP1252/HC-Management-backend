package com.perezbrandon.HC_Management.respository;

import com.perezbrandon.HC_Management.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Finds admin by username
    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    Optional<Admin> adminByUsername(String username);

    // Finds admin by email
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    Optional<Admin> adminByEmail(String email);
}
