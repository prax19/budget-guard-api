package com.prax19.repositories;

import com.prax19.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findById(Long id);

    List<Budget> findAllByOwnerId(Long userId);

}
