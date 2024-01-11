package com.prax19.services;

import com.prax19.services.validators.EmailValidator;
import com.prax19.services.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prax19.entities.UserDetails;
import com.prax19.entities.AppUserRole;
import com.prax19.requests.RegistrationRequest;

@Service
public class RegistrationService {

    private final static String EMAIL_NOT_VALID_MSG = "login '%s' not valid";
    private final static String PASSWORD_NOT_VALID_MSG = "password not valid";

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserDetailsService userDetailsService;

    public UserDetails register(RegistrationRequest request) {

        if(!emailValidator.test(request.getEmail()))
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID_MSG, request.getEmail()));
        if(!passwordValidator.test(request.getPassword()))
            throw new IllegalStateException(String.format(PASSWORD_NOT_VALID_MSG));

        return userDetailsService.signUpUser(
            new UserDetails(
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
