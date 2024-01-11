package com.prax19.services;

import com.prax19.entities.Budget;
import com.prax19.entities.BudgetOperation;
import com.prax19.entities.UserDetails;
import com.prax19.repositories.BudgetRepository;
import com.prax19.requests.BudgetOperationRequest;
import com.prax19.requests.BudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BudgetService {

    private final static String RESOURCE_ACCESS_DENIED_MSG = "no access to budget with id %s";

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetOperationService budgetOperationService;

    public Budget addNewBudget(UserDetails userDetails, BudgetRequest request) {
        Budget budget = new Budget(
                request.getName(),
                userDetails.getId()
        );
        budgetRepository.save(budget);
        return budget;
    }

    public Budget setBudget(UserDetails userDetails, BudgetRequest request, Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElse(new Budget(
                        request.getName(),
                        userDetails.getId()
                ));
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        if (Objects.nonNull(request.getName()))
            budget.setName(request.getName());

        budgetRepository.saveAndFlush(budget);
        return budget;
    }

    public Budget getBudgetById(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        return budget;
    }

    public List<Budget> getAllOwnedBudgets(UserDetails userDetails) {
        return budgetRepository.findAllByOwnerId(userDetails.getId());
    }

    public Budget deleteBudget(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        for(Long operationId: budget.getOperations())
            budgetOperationService.deleteBudgetOperation(
                    userDetails,
                    budget,
                    operationId
            );

        budgetRepository.deleteById(id);
        return budget;
    }

    public BudgetOperation addBudgetOperation(
            UserDetails userDetails,
            Long budgetId,
            BudgetOperationRequest request
    ) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budgetId));

        BudgetOperation operation = budgetOperationService.addBudgetOperation(userDetails, budget, request);
        budget.getOperations().add(operation.getId());

        budgetRepository.saveAndFlush(budget);
        return operation;

    }

    public BudgetOperation setBudgetOperation(
            UserDetails userDetails,
            Long budgetId,
            Long operationId,
            BudgetOperationRequest request
    ) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budgetId));

        BudgetOperation operation;
        if(!budget.getOperations().contains(operationId))
            operation = addBudgetOperation(userDetails, budgetId, request);
        else {
            operation = budgetOperationService.editBudgetOperation(
                    userDetails,
                    budget,
                    operationId,
                    request
            );
        }
        return operation;

    }

    public BudgetOperation deleteBudgetOperation(
            UserDetails userDetails,
            Long budgetId,
            Long operationId
    ) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budgetId));
        if(!budget.getOperations().contains(operationId))
            throw new NoSuchElementException();

        BudgetOperation operation = budgetOperationService.deleteBudgetOperation(
                userDetails,
                budget,
                operationId
        );
        budget.getOperations().remove(operationId);
        budgetRepository.saveAndFlush(budget);
        return operation;
    }

    public BudgetOperation getBudgetOperation(
            UserDetails userDetails,
            Long budgetId,
            Long operationId
    ) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budgetId));
        if(!budget.getOperations().contains(operationId))
            throw new NoSuchElementException();

        return budgetOperationService.getBudgetOperation(userDetails, budget, operationId);
    }

    public List<BudgetOperation> getAllBudgetOperations(
            UserDetails userDetails,
            Long budgetId
    ) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow();
        if (!budget.getOwnerId().equals(userDetails.getId()))
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budgetId));

        return  budgetOperationService.getAllBudgetOperations(
                userDetails,
                budget
        );
    }

}
