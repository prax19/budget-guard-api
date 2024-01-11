package com.prax19.controllers;

import com.prax19.entities.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prax19.requests.RegistrationRequest;
import com.prax19.services.RegistrationService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        UserDetails userDetails = registrationService.register(request);
        return new ResponseEntity<>(
                "User " + userDetails.getEmail() + " registered successfully!", HttpStatus.OK
        );
    }
    
}
