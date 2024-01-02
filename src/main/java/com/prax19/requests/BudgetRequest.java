package com.prax19.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BudgetRequest {


    private Long id;
    private String name;

    private BudgetRequest() {
        id = null;
        name = null;
    }
    @JsonCreator
    public BudgetRequest(
            @JsonProperty("id")Long id,
            @JsonProperty("name") String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
