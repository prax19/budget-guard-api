package com.prax19.repositories;

import com.prax19.entities.BudgetOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetOperationRepository extends JpaRepository<BudgetOperation, Long> {


    BudgetOperation getBudgetOperationById(Long id);
    List<BudgetOperation> getBudgetOperationsByBudgetIdOrderByDateTimeAsc(Long id);

    void deleteBudgetOperationById(Long id);

}
