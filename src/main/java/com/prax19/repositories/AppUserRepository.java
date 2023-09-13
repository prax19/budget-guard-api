package com.prax19.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prax19.entities.AppUser;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository{

    Optional<AppUser> findByEmail(String email);
    
}
