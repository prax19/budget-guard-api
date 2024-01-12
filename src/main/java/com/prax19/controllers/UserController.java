package com.prax19.controllers;

import com.prax19.entities.UserDetails;
import com.prax19.responses.UserInfoResponse;
import com.prax19.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prax19.requests.RegistrationRequest;
import com.prax19.services.RegistrationService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    @Deprecated
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        UserDetails userDetails = registrationService.register(request);
        return new ResponseEntity<>(
                "User " + userDetails.getEmail() + " registered successfully!", HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Long id) {
        UserDetails userDetails = userDetailsService.loadUserById(id);
        UserInfoResponse userInfo = new UserInfoResponse(
                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getAppUserRole().name(),
                userDetails.getLocked(),
                userDetails.getEnabled()
        );
        return new ResponseEntity<>(
                userInfo, HttpStatus.OK
        );
    }
    
}
