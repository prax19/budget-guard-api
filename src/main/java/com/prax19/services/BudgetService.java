package com.prax19.services;

import com.prax19.entities.Budget;
import com.prax19.entities.UserDetails;
import com.prax19.repositories.BudgetRepository;
import com.prax19.requests.BudgetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Objects;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public void addNewBudget(UserDetails userDetails, BudgetRequest request) {
        Budget budget = new Budget(
                request.getName(),
                userDetails.getUser()
        );
        budgetRepository.save(budget);
    }

    public void setBudget(UserDetails userDetails, BudgetRequest request, Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElse(new Budget(
                        request.getName(),
                        userDetails.getUser()
                ));
        if (budget.getOwner().getDetails().getId() != userDetails.getId())
            throw new ResourceAccessException("No permission to budget with id: " + id);

        if (Objects.nonNull(request.getName()))
            budget.setName(request.getName());

        budgetRepository.saveAndFlush(budget);
    }

    public Budget getBudgetById(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (budget.getOwner().getDetails().getId() != userDetails.getId())
            throw new ResourceAccessException("No permission to budget with id: " + id);

        return budget;
    }

    public List<Budget> getAllBudgets(UserDetails userDetails) {
        return budgetRepository.findAllByOwner(userDetails.getUser());
    }

    public void deleteBudget(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (budget.getOwner().getDetails().getId() != userDetails.getId())
            throw new ResourceAccessException("No permission to budget with id: " + id);

        budgetRepository.deleteById(id);
    }

}
