package com.perezbrandon.HC_Management.controller;

import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/admin/patient-list")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @DeleteMapping("/admin/delete-patient/{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
        patientService.deletePatient(id);
    }



}
