package com.prax19.budgetguardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.prax19"})
public class BudgetGuardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetGuardApiApplication.class, args);
	}

}
