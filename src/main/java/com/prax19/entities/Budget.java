package com.prax19.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User owner;

}
