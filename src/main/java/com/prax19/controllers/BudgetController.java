package com.prax19.controllers;

import com.prax19.entities.Budget;
import com.prax19.entities.BudgetOperation;
import com.prax19.entities.UserDetails;
import com.prax19.requests.BudgetOperationRequest;
import com.prax19.requests.BudgetRequest;
import com.prax19.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public void createNewBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetRequest request) {
         budgetService.addNewBudget(userDetails, request);
    }

    @PutMapping("/{id}")
    public void setBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetRequest request,
            @PathVariable Long id) {
        budgetService.setBudget(userDetails, request, id);
    }

    @GetMapping("/{id}")
    public void getBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        Budget budget = budgetService.getBudgetById(userDetails, id);
        System.out.println(budget.getId() + ", " + budget.getName());
    }

    @GetMapping
    public void getAllOwnedBudgets(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Budget> list = budgetService.getAllOwnedBudgets(userDetails);
        for(Budget budget: list)
            System.out.println(budget.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        budgetService.deleteBudget(userDetails, id);
    }

    @PostMapping("/{budgetId}/operation")
    public void createBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetOperationRequest request,
            @PathVariable Long budgetId
    ) {
        budgetService.addBudgetOperation(
                userDetails,
                budgetId,
                request);
    }

    @PutMapping("/{budgetId}/operation/{operationId}")
    public void setBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetOperationRequest request,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        budgetService.setBudgetOperation(
                userDetails,
                budgetId,
                operationId,
                request
        );
    }

    @DeleteMapping("/{budgetId}/operation/{operationId}")
    public void deleteBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        budgetService.deleteBudgetOperation(
                userDetails,
                budgetId,
                operationId
        );
    }

    @GetMapping("/{budgetId}/operation/{operationId}")
    public void getBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        BudgetOperation operation = budgetService.getBudgetOperation(userDetails, budgetId, operationId);
        System.out.println(operation.getId() + ", " + operation.getUserId() + ", " + operation.getName() + ", " + operation.getOperationValue());
    }

    @GetMapping("/{budgetId}/operations")
    public void getAllBudgetOperations(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId
    ) {
        List<BudgetOperation> budgetOperations = budgetService.getAllBudgetOperations(userDetails, budgetId);
        for(BudgetOperation operation: budgetOperations)
            System.out.println(operation.getId() + ", " + operation.getUserId() + ", " + operation.getName() + ", " + operation.getOperationValue());
    }

}
