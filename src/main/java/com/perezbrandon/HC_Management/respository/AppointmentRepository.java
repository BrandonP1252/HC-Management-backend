package com.perezbrandon.HC_Management.respository;

import com.perezbrandon.HC_Management.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    // Gets all appointments for a specific patient
    @Query("SELECT a FROM Appointment a WHERE a.patient_id = :patient_id")
    List<Appointment> appointmentByPatient(Integer patient_id);

    // Gets all appointments for a specific doctor
    @Query("SELECT a FROM Appointment a WHERE a.doctor_id = :doctor_id")
    List<Appointment> appointmentByDoctor(Integer doctor_id);
}
