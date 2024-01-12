package com.prax19.controllers;

import com.prax19.requests.AuthenticationRequest;
import com.prax19.requests.RegistrationRequest;
import com.prax19.responses.AuthenticationResponse;
import com.prax19.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegistrationRequest request
    ) {
        return new ResponseEntity<>(
                authenticationService.register(request), HttpStatus.OK
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return new ResponseEntity<>(
                authenticationService.authenticate(request), HttpStatus.OK
        );
    }

}
