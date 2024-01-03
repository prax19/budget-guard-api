package com.prax19.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class BudgetOperation {

    @Id
    @GeneratedValue
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
//    @NonNull
//    private Timestamp timestamp;

    @NonNull
    private String name;

    @NonNull
    private Long budgetId;

    @NonNull
    private Long userId;

    @NonNull
    private Float operationValue;

}
