package com.prax19.controllers;

import com.prax19.entities.Budget;
import com.prax19.entities.BudgetOperation;
import com.prax19.entities.UserDetails;
import com.prax19.requests.BudgetOperationRequest;
import com.prax19.requests.BudgetRequest;
import com.prax19.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<String> createNewBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetRequest request
    ) {
         Budget budget = budgetService.addNewBudget(userDetails, request);
         return new ResponseEntity<>(
                 "Successfully created new budget '" + budget.getName() + "'!", HttpStatus.CREATED
         );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> setBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetRequest request,
            @PathVariable Long id
    ) {
        Budget budget = budgetService.setBudget(userDetails, request, id);
        return new ResponseEntity<>(
                "Successfully updated budget '" + budget.getName() + "'!", HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        Budget budget = budgetService.getBudgetById(userDetails, id);
        return new ResponseEntity<>(
                budget, HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllOwnedBudgets(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Budget> budgets = budgetService.getAllOwnedBudgets(userDetails);
        return new ResponseEntity<>(
                budgets, HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id
    ) {
        Budget deleteBudget = budgetService.deleteBudget(userDetails, id);
        return new ResponseEntity<>(
                "Successfully deleted budget '" + deleteBudget.getName() + "'!", HttpStatus.OK
        );
    }

    @PostMapping("/{budgetId}/operation")
    public ResponseEntity<String> createBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetOperationRequest request,
            @PathVariable Long budgetId
    ) {
        BudgetOperation operation = budgetService.addBudgetOperation(
                userDetails,
                budgetId,
                request
        );
        return new ResponseEntity<>(
                "Successfully created operation '" + operation.getName() +
                        "' in budget " + operation.getBudgetId() +"!", HttpStatus.CREATED
        );
    }

    @PutMapping("/{budgetId}/operation/{operationId}")
    public ResponseEntity<String> setBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody BudgetOperationRequest request,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        BudgetOperation operation = budgetService.setBudgetOperation(
                userDetails,
                budgetId,
                operationId,
                request
        );
        return new ResponseEntity<>(
                "Successfully updated operation '" + operation.getName() +
                        "' in budget " + operation.getBudgetId() +"!", HttpStatus.OK
        );
    }

    @DeleteMapping("/{budgetId}/operation/{operationId}")
    public ResponseEntity<String> deleteBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        BudgetOperation deletedOperation = budgetService.deleteBudgetOperation(
                userDetails,
                budgetId,
                operationId
        );
        return new ResponseEntity<>(
                "Successfully deleted operation '" + deletedOperation.getName() +
                        "' in budget " + deletedOperation.getBudgetId() +"!", HttpStatus.OK
        );
    }

    @GetMapping("/{budgetId}/operation/{operationId}")
    public ResponseEntity<BudgetOperation> getBudgetOperation(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId,
            @PathVariable Long operationId
    ) {
        BudgetOperation operation = budgetService.getBudgetOperation(userDetails, budgetId, operationId);
        return new ResponseEntity<>(
                operation, HttpStatus.OK
        );
    }

    @GetMapping("/{budgetId}/operations")
    public ResponseEntity<List<BudgetOperation>> getAllBudgetOperations(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long budgetId
    ) {
        List<BudgetOperation> operations = budgetService.getAllBudgetOperations(userDetails, budgetId);
        return new ResponseEntity<>(
                operations, HttpStatus.OK
        );
    }

}
