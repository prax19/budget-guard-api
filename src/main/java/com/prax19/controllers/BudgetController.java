package com.prax19.controllers;

import com.prax19.entities.Budget;
import com.prax19.entities.UserDetails;
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

}
