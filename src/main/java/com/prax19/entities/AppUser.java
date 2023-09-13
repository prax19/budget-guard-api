package com.prax19.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(
        name = "users_sequence",
        sequenceName = "users_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "users_sequence"
    )
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    private Boolean locked;
    
    private Boolean enabled;

    public AppUser(
        String name, 
        String username, 
        String email, 
        String password, 
        AppUserRole appUserRole, 
        Boolean locked,
        Boolean enabled
    ) {
        setName(name);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setAppUserRole(appUserRole);
        setLocked(locked);
        setEnabled(enabled);
    }

    public AppUser() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = 
            new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((appUserRole == null) ? 0 : appUserRole.hashCode());
        result = prime * result + ((locked == null) ? 0 : locked.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppUser other = (AppUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (appUserRole != other.appUserRole)
            return false;
        if (locked == null) {
            if (other.locked != null)
                return false;
        } else if (!locked.equals(other.locked))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        return true;
    }
    
}
