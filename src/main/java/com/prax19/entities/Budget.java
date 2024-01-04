package com.prax19.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private Long id;

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("ownerId")
    private Long ownerId;

    @ElementCollection
    @JsonProperty("operations")
    private List<Long> operations;

}
