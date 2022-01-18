package com.expense.service;

import com.expense.exceptions.ExpenseException;
import com.expense.model.Contribution;
import com.expense.model.Expense;
import com.expense.model.ExpenseStatus;
import com.expense.model.User;
import com.expense.model.UserShare;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class UserService {
	private UserRepository userRepository = new UserRepository();
	private ExpenseRepository expenseRepository = new ExpenseRepository();

	public User registerUser(String emailId, String name, String phoneNumber) throws ExpenseException {
		if (userRepository.findByEmailId(emailId) != null) {
			throw new ExpenseException("User already exists, EmailId: " + emailId);
		}

		User user = User.builder()
				.emailId(emailId)
				.name(name)
				.phoneNumber(phoneNumber)
				.build();

		user = userRepository.save(user);
		log.info("User registered successfully, EmailId: " + emailId);

		return user;
	}

	public User getUserByEmailId(String emailId) throws ExpenseException {
		User user = userRepository.findByEmailId(emailId);
		if (user == null) {
			throw new ExpenseException("User not found, EmailId: " + emailId);
		}

		return user;
	}

	public void contributeToExpense(UUID expenseId, String emailId, Double amount) throws ExpenseException {
		Expense expense = expenseRepository.findById(expenseId);

		if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
			throw new ExpenseException("Invalid expense State. ExpenseId: " + expenseId);
		}
		if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
			throw new ExpenseException("Expense is already settled. ExpenseId: " + expenseId);
		}

		UserShare userShare = expense.getExpenseGroup().getUserContributions().get(emailId);

		if (userShare.getAmount() < amount) {
			throw new ExpenseException("User " + emailId + " contribution " + amount + " exceeded the share " + userShare.getAmount());
		}

		Contribution contribution = Contribution.builder()
				.contributionId(UUID.randomUUID())
				.contributionAmount(userShare.getAmount())
				.contributionDate(LocalDateTime.now())
				.transactionId("T: " + Instant.EPOCH)
				.build();

		userShare.getContribution().add(contribution);
		log.info("User {} contributed {} to expense {}", emailId, amount, expenseId);
	}
}
