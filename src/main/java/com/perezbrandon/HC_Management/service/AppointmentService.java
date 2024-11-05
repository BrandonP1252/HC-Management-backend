package com.perezbrandon.HC_Management.service;

import com.perezbrandon.HC_Management.dto.AppointmentRegReq;
import com.perezbrandon.HC_Management.model.Appointment;
import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.model.Status;
import com.perezbrandon.HC_Management.respository.AppointmentRepository;
import com.perezbrandon.HC_Management.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getDoctorAppointments(Integer doctor_id) {
        return appointmentRepository.appointmentByDoctor(doctor_id);
    }

    public List<Appointment> getPatientAppointments(Integer patient_id) {
        return appointmentRepository.appointmentByPatient(patient_id);
    }

    public Appointment addAppointment(AppointmentRegReq appointment) {
        Appointment newAppointment = convertToEntity(appointment);
        appointmentRepository.save(newAppointment);
        return newAppointment;
    }

    public Appointment convertToEntity(AppointmentRegReq appointment) {
        Appointment newAppointment = new Appointment();
        Optional<Patient> patientByUsername = patientRepository.patientByUsername(appointment.getUsername());
        if (patientByUsername.isEmpty()) {
            throw  new UsernameNotFoundException("USER DOES NOT EXIST");
        }
        Patient patient = patientByUsername.get();
        newAppointment.setPatient_id(patient.getId());
        newAppointment.setDoctor_id(appointment.getDoctor_id());
        LocalDate date = LocalDate.parse(appointment.getAppointment_date());
        newAppointment.setAppointment_date(Date.valueOf(date));
        newAppointment.setReason(appointment.getReason());
        newAppointment.setStatus(Status.valueOf(appointment.getStatus().toUpperCase()));
        newAppointment.setCreated_at(new Timestamp(System.currentTimeMillis()));
        newAppointment.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        LocalTime time = LocalTime.parse(appointment.getAppointment_time());
        newAppointment.setAppointment_time(Time.valueOf(time));

        return newAppointment;

    }
}
