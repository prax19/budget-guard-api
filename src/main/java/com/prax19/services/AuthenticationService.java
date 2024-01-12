package com.prax19.services;

import com.prax19.config.JwtService;
import com.prax19.entities.AppUserRole;
import com.prax19.entities.UserDetails;
import com.prax19.exceptions.user.UserNotFoundException;
import com.prax19.repositories.UserDetailsRepository;
import com.prax19.requests.AuthenticationRequest;
import com.prax19.requests.RegistrationRequest;
import com.prax19.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {
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
