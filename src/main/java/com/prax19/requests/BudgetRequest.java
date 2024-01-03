package com.prax19.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BudgetRequest {

    private final String name;

    @JsonCreator
    public BudgetRequest(
            @JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
