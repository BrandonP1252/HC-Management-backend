package com.perezbrandon.HC_Management.controller;

import com.perezbrandon.HC_Management.dto.AppointmentRegReq;
import com.perezbrandon.HC_Management.model.Appointment;
import com.perezbrandon.HC_Management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/user/get-doctor-appointment/{doctor_id}")
    public List<Appointment> getDoctorAppointments(@PathVariable("doctor_id") Integer doctor_id) {
        return appointmentService.getDoctorAppointments(doctor_id);
    }

    @GetMapping("/user/get-patient-appointment/{patient_id}")
    public List<Appointment> getPatientAppointments(@PathVariable("patient_id") Integer patient_id) {
        return appointmentService.getPatientAppointments(patient_id);
    }

    @PostMapping("/user/add-appointment")
    public Appointment addAppointment(@RequestBody AppointmentRegReq appointmentRegReq) {
        return appointmentService.addAppointment(appointmentRegReq);
    }

}
