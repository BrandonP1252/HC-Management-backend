package com.perezbrandon.HC_Management.controller;

import com.perezbrandon.HC_Management.config.JwtService;
import com.perezbrandon.HC_Management.dto.UserRegReq;
import com.perezbrandon.HC_Management.model.Patient;
import com.perezbrandon.HC_Management.respository.PatientRepository;
import com.perezbrandon.HC_Management.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    private final PatientRepository patientRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService userDetailsService, PatientRepository patientRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.patientRepository = patientRepository;
    }

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



    @GetMapping("/user/users")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
