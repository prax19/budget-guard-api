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

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    @NonNull
    private Budget budget;

    @NonNull
    private Float operationValue;

}
