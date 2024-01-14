package com.prax19.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class BudgetOperationRequest {

    private final String name;
    private final LocalDateTime dateTime;
    private final Float operationValue;

    public BudgetOperationRequest(
            @JsonProperty("name") String name,
            @JsonProperty("dateTime") LocalDateTime dateTime,
            @JsonProperty("value") Float operationValue
    ) {
        this.name = name;
        this.dateTime = dateTime;
        this.operationValue = operationValue;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Float getOperationValue() {
        return operationValue;
    }
}
