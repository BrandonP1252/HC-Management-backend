package com.perezbrandon.HC_Management.controller;

import com.perezbrandon.HC_Management.model.Doctor;
import com.perezbrandon.HC_Management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/admin/doctor-list")
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @DeleteMapping("/admin/delete-doctor/{id}")
    public void deleteDoctor(@PathVariable("id") Integer id) {
        doctorService.deleteDoctor(id);
    }

}
