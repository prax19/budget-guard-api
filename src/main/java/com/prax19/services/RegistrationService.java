package com.prax19.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prax19.entities.AppUser;
import com.prax19.entities.AppUserRole;
import com.prax19.requests.RegistrationRequest;

@Service
public class RegistrationService {

    private final static String EMAIL_NOT_VALID_MSG = "email \'%s\' not valid";

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private AppUserService appUserService;

    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID_MSG, request.getEmail()));
        }

        return appUserService.signUpUser(
            new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER,
                false,
                true
            )
        );
    }
    
}
