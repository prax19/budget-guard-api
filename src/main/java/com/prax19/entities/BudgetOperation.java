package com.prax19.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class BudgetOperation {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
//    @NonNull
//    private Timestamp timestamp;

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("budgetId")
    private Long budgetId;

    @NonNull
    @JsonProperty("userId")
    private Long userId;

    @NonNull
    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @NonNull
    @JsonProperty("value")
    private Float operationValue;

}
