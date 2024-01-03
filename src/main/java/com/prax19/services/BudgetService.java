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

    private final static String RESOURCE_ACCESS_DENIED_MSG = "no access to budget with id %s";

    @Autowired
    private BudgetRepository budgetRepository;

    public void addNewBudget(UserDetails userDetails, BudgetRequest request) {
        Budget budget = new Budget(
                request.getName(),
                userDetails.getUser().getId()
        );
        budgetRepository.save(budget);
    }

    public void setBudget(UserDetails userDetails, BudgetRequest request, Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElse(new Budget(
                        request.getName(),
                        userDetails.getUser().getId()
                ));
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        if (Objects.nonNull(request.getName()))
            budget.setName(request.getName());

        budgetRepository.saveAndFlush(budget);
    }

    public Budget getBudgetById(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        return budget;
    }

    public List<Budget> getAllOwnedBudgets(UserDetails userDetails) {
        return budgetRepository.findAllByOwnerId(userDetails.getId());
    }

    public void deleteBudget(UserDetails userDetails, Long id) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        if (budget.getOwnerId() != userDetails.getId())
            throw new ResourceAccessException(String.format(RESOURCE_ACCESS_DENIED_MSG, id));

        budgetRepository.deleteById(id);
    }

}
