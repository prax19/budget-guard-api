package com.prax19.services;

import com.prax19.entities.Budget;
import com.prax19.entities.BudgetOperation;
import com.prax19.entities.UserDetails;
import com.prax19.repositories.BudgetOperationRepository;
import com.prax19.requests.BudgetOperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BudgetOperationService {

    private final static String RESOURCE_ACCESS_DENIED_MSG = "no access to budget with id %s";

    @Autowired
    private BudgetOperationRepository budgetOperationRepository;

    public BudgetOperation addBudgetOperation(
            UserDetails userDetails,
            Budget budget,
            BudgetOperationRequest request
    ) {
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budget.getId()));

        BudgetOperation operation = new BudgetOperation(
                request.getName(),
                budget.getId(),
                userDetails.getId(),
                request.getOperationValue()
        );
        budgetOperationRepository.save(operation);
        return operation;
    }

    public BudgetOperation editBudgetOperation(
            UserDetails userDetails,
            Budget budget,
            Long operationId,
            BudgetOperationRequest request
    ) {
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budget.getId()));

        BudgetOperation operation = budgetOperationRepository.getBudgetOperationById(operationId);
        if(!budget.getOperations().contains(operation.getId()))
            throw new NoSuchElementException();

        if (Objects.nonNull(request.getName()))
            operation.setName(request.getName());

        if (Objects.nonNull(request.getOperationValue()))
            operation.setOperationValue(request.getOperationValue());

        budgetOperationRepository.saveAndFlush(operation);
        return operation;

    }

    @Transactional
    public void deleteBudgetOperation(
            UserDetails userDetails,
            Budget budget,
            Long operationId
    ) {
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budget.getId()));
        if(!budget.getOperations().contains(operationId))
            throw new NoSuchElementException();

        budgetOperationRepository.deleteBudgetOperationById(operationId);
    }

    public BudgetOperation getBudgetOperation(
            UserDetails userDetails,
            Budget budget,
            Long operationId
    ) {
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budget.getId()));
        if(!budget.getOperations().contains(operationId))
            throw new NoSuchElementException();

        return budgetOperationRepository.getBudgetOperationById(operationId);
    }

    public List<BudgetOperation> getAllBudgetOperations(
            UserDetails userDetails,
            Budget budget
    ) {
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, budget.getId()));

        return budgetOperationRepository.getBudgetOperationsByBudgetId(budget.getId());
    }

}
