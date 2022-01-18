package com.expense.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Expense {
	private UUID expenseId;
	private String title;
	private String description;
	private String userEmailId;
	private LocalDateTime expenseDate;
	private Double amount;
	private ExpenseGroup expenseGroup;
	private ExpenseStatus expenseStatus;
}
