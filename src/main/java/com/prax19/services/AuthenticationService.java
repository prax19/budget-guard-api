package com.prax19.services;

import com.prax19.config.JwtService;
import com.prax19.entities.AppUserRole;
import com.prax19.entities.UserDetails;
import com.prax19.exceptions.user.UserAlreadyExistsException;
import com.prax19.exceptions.user.UserNotFoundException;
import com.prax19.repositories.UserDetailsRepository;
import com.prax19.requests.AuthenticationRequest;
import com.prax19.requests.RegistrationRequest;
import com.prax19.responses.AuthenticationResponse;
import com.prax19.services.validators.user.EmailValidator;
import com.prax19.services.validators.user.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final static String EMAIL_NOT_VALID_MSG = "login '%s' not valid";
    private final static String PASSWORD_NOT_VALID_MSG = "password not valid";
    private final static String USER_ALREADY_EXISTS_MSG = "user with email '%s' already exists";

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
        if(!emailValidator.test(request.getLogin()))
            throw new IllegalStateException(String.format(EMAIL_NOT_VALID_MSG, request.getLastName()));
        if(!passwordValidator.test(request.getPassword()))
            throw new IllegalStateException(String.format(PASSWORD_NOT_VALID_MSG));
        boolean userExists = userDetailsRepository.findByEmail(request.getLogin()).isPresent();
        if(userExists)
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG, request.getLogin()));

        UserDetails user = new UserDetails(
                request.getFirstName(),
                request.getLastName(),
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                AppUserRole.USER,
                false,
                true
        );
        userDetailsRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = userDetailsRepository.findByEmail(request.getLogin())
                .orElseThrow(UserNotFoundException::new);
        String jwtToken = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
