package com.perezbrandon.HC_Management.respository;

import com.perezbrandon.HC_Management.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    // Gets all medical records from a specific patient
    @Query("SELECT m FROM MedicalRecord m WHERE m.patient_id = :patient_id")
    List<MedicalRecord> findByPatient(Integer patient_id);
}
