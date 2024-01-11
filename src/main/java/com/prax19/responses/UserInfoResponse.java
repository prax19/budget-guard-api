package com.prax19.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoResponse {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String role;

    private final Boolean locked;

    private final Boolean enabled;

    public UserInfoResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("role") String role,
            @JsonProperty("locked") Boolean locked,
            @JsonProperty("enabled") Boolean enabled
    ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

}
