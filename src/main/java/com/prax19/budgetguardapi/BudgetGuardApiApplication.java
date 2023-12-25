package com.prax19.budgetguardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.prax19.repositories")
@ComponentScan(basePackages = {"com.prax19"})
@EntityScan(basePackages = {"com.prax19.entities"})
public class BudgetGuardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetGuardApiApplication.class, args);
	}

}
