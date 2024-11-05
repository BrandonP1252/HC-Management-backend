package com.perezbrandon.HC_Management.controller;

import com.perezbrandon.HC_Management.config.JwtService;
import com.perezbrandon.HC_Management.dto.AdminRegReq;
import com.perezbrandon.HC_Management.dto.DoctorRegReq;
import com.perezbrandon.HC_Management.dto.PatientRegReq;
import com.perezbrandon.HC_Management.dto.UserRegReq;
import com.perezbrandon.HC_Management.model.Admin;
import com.perezbrandon.HC_Management.model.Doctor;
import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.service.AdminService;
import com.perezbrandon.HC_Management.service.CustomUserDetailsService;
import com.perezbrandon.HC_Management.service.DoctorService;
import com.perezbrandon.HC_Management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    private final PatientService patientService;

    @Autowired
    private final AdminService adminService;

    @Autowired
    private final DoctorService doctorService;


    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService userDetailsService, PatientService patientService, AdminService adminService, DoctorService doctorService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.patientService = patientService;
        this.adminService = adminService;
        this.doctorService = doctorService;
    }

    // USER LOGIN

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestBody UserRegReq userRegReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegReq.username(), userRegReq.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetailsService.loadUserByUsername(userRegReq.username()));
        }
        else {
            throw new UsernameNotFoundException("Username or password does not exist.");
        }
    }

    // ALL USER REGISTRATIONS

    @PostMapping("/register")
    public Patient registerUser(@RequestBody PatientRegReq patientRegReq) {
        return patientService.registerPatient(patientRegReq);
    }

    @PostMapping("/admin/register/admin")
    public Admin registerAdmin(@RequestBody AdminRegReq adminRegReq) {
        return adminService.registerAdmin(adminRegReq);
    }

    @PostMapping("/admin/register/doctor")
    public Doctor registerDoctor(@RequestBody DoctorRegReq doctorRegReq) {
        return doctorService.registerDoctor(doctorRegReq);
    }

}
