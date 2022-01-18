package com.expense.repository;

import com.expense.model.Expense;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExpenseRepository {
	private static final Map<UUID, Expense> expenseMap = new HashMap<>();

	public Expense save(Expense expense) {
		expenseMap.put(expense.getExpenseId(), expense);

		return expense;
	}

	public Expense findById(UUID id) {
		return expenseMap.get(id);
	}
}
