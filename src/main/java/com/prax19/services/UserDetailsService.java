package com.prax19.services;

import com.prax19.entities.UserDetails;
import com.prax19.exceptions.user.UserAlreadyExistsException;
import com.prax19.exceptions.user.UserNotFoundException;
import com.prax19.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final static String USER_NOT_FOUND_EMAIL_MSG = "user with email '%s' not found";
    private final static String USER_NOT_FOUND_ID_MSG = "user with id '%s' not found";
    private final static String USER_ALREADY_EXISTS_MSG = "user with email '%s' already exists";

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email)
            .orElseThrow(() -> 
                new UserNotFoundException(String.format(USER_NOT_FOUND_EMAIL_MSG, email))
            );
    }

    public UserDetails loadUserById(Long id) throws NoSuchElementException {
        return userDetailsRepository.findById(id)
            .orElseThrow(() ->
                    new UserNotFoundException(String.format(USER_NOT_FOUND_ID_MSG, id))
            );
    }

    public UserDetails signUpUser(UserDetails userDetails) {
        boolean userExists = userDetailsRepository.findByEmail(userDetails.getEmail()).isPresent();
        if(userExists)
            throw new UserAlreadyExistsException(String.format(USER_ALREADY_EXISTS_MSG, userDetails.getEmail()));

        String encodedPassword = bCryptPasswordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(encodedPassword);

        userDetailsRepository.save(userDetails);

        return userDetails;
    }
    
}
