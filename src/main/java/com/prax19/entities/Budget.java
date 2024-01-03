package com.prax19.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NonNull
    private User owner;

    @OneToMany(mappedBy = "budget")
    private List<BudgetOperation> operations;

}
