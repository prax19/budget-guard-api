package com.prax19.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BudgetOperationRequest {

    private final String name;
    private final Float operationValue;

    public BudgetOperationRequest(
            @JsonProperty("name") String name,
            @JsonProperty("value") Float operationValue
    ) {
        this.name = name;
        this.operationValue = operationValue;
    }

    public String getName() {
        return name;
    }

    public Float getOperationValue() {
        return operationValue;
    }
}
