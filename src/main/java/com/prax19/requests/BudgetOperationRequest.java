package com.prax19.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BudgetOperationRequest {

    private Long id;
    private String name;
    private Long userId;
    private Float operationValue;

    public BudgetOperationRequest(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("userId") Long userId,
            @JsonProperty("value") Float operationValue
    ) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.operationValue = operationValue;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public Float getOperationValue() {
        return operationValue;
    }
}
