package com.expense.service;

import com.expense.exceptions.ExpenseException;
import com.expense.model.Contribution;
import com.expense.model.Expense;
import com.expense.model.ExpenseGroup;
import com.expense.model.ExpenseStatus;
import com.expense.model.User;
import com.expense.model.UserShare;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class ExpenseService {
	private final ExpenseRepository expenseRepository = new ExpenseRepository();
	private final UserService userService = new UserService();

	public Expense createExpense(String title, String description, Double amount,
								 String userEmailId, LocalDateTime expenseDate) {
		Expense expense = Expense.builder()
				.expenseId(UUID.randomUUID())
				.title(title)
				.description(description)
				.userEmailId(userEmailId)
				.amount(amount)
				.expenseDate(expenseDate)
				.expenseGroup(new ExpenseGroup())
				.expenseStatus(ExpenseStatus.CREATED)
				.build();

		expense = expenseRepository.save(expense);
		log.info("Expense {} created successfully.", expense.getTitle());

		return expense;
	}

	public Expense getExpense(UUID expenseId) throws ExpenseException {
		Expense expense = expenseRepository.findById(expenseId);
		if (expense == null) {
			throw new ExpenseException("Expense not found, ExpenseId: " + expenseId.toString());
		}

		return expense;
	}

	public void addUsersToExpense(UUID expenseId, String emailId) throws ExpenseException {
		Expense expense = getExpense(expenseId);

		expense.getExpenseGroup().getGroupMembers().add(userService.getUserByEmailId(emailId));
		log.info("User {} added to expense {}", emailId, expenseId);

		// Notify User
	}

	public void addUserShare(UUID expenseId, String userEmailId, Double amount) throws ExpenseException {
		Expense expense = getExpense(expenseId);
		UserShare userShare = new UserShare(userEmailId, amount);

		expense.getExpenseGroup().getUserContributions().putIfAbsent(userEmailId, userShare);

		expense.setExpenseStatus(ExpenseStatus.PENDING);
		log.info("User {} share to expense {} is {}", userEmailId, expenseId, amount);
	}

	public Set<User> getGroupMembers(UUID expenseId) throws ExpenseException {
		Expense expense = getExpense(expenseId);
		return expense.getExpenseGroup().getGroupMembers();
	}

	public void setExpenseStatusToSettled(UUID expenseId, ExpenseStatus settled) throws ExpenseException {
		Expense expense = getExpense(expenseId);
		Double totalAmount = 0d;

		for (Map.Entry<String, UserShare> entry : expense.getExpenseGroup().getUserContributions().entrySet()) {
			UserShare userShare = entry.getValue();

			for (Contribution contribution : userShare.getContribution()) {
				totalAmount += contribution.getContributionAmount();
			}
		}

		if (totalAmount.equals(expense.getAmount())) {
			expense.setExpenseStatus(settled);
		} else {
			throw new ExpenseException("Unable to settle expense. All group members are not contributed yet. ExpenseId: " + expenseId);
		}

		log.info("Expense {} settled successfully.", expenseId);
	}
}
