package com.prax19.services.validators;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PasswordValidator implements Predicate<String> {

    @Override
    public boolean test(String t) {
        //TODO: Email regex
        return true;
    }

}
