package com.expense;

import com.expense.exceptions.ExpenseException;
import com.expense.model.Expense;
import com.expense.model.ExpenseStatus;
import com.expense.model.User;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.UserRepository;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class ExpenseMain {
	static UserService userService;
	static ExpenseService expenseService;

	public static void main(String[] args) {
		try {
			userService = new UserService();
			expenseService = new ExpenseService();

			createTestUsers();
			Expense expense = createLunchExpense();
			addUsersToExpense(expense.getExpenseId());
			bifurcateExpense(expense.getExpenseId());

			Set<User> groupMembers = expenseService.getGroupMembers(expense.getExpenseId());
			for (User user : groupMembers) {
				contributeToExpense(expense.getExpenseId(), user.getEmailId());
			}

			expenseService.setExpenseStatusToSettled(expense.getExpenseId(), ExpenseStatus.SETTLED);
		} catch (ExpenseException e) {
			log.error(e.getMsg());
		}
	}

	private static void createTestUsers() throws ExpenseException {
		User user1 = userService.registerUser("avishek@gmail.com", "Avishek Das", "8976504834");
		User user2 = userService.registerUser("ramesh@gmail.com", "Ramesh Sahoo", "9088305690");
		User user3 = userService.registerUser("subhasis@gmail.com", "Subhasis Panda", "7809411306");
	}

	private static Expense createLunchExpense() {
		return expenseService.createExpense("Company Lunch", "Lunch in ThreeKing", 1800.00,
				"avishek@gmail.com", LocalDateTime.of(2021, Month.APRIL, 19, 11, 34));
	}

	private static void addUsersToExpense(UUID expenseId) throws ExpenseException {
		expenseService.addUsersToExpense(expenseId, expenseService.getExpense(expenseId).getUserEmailId());
		expenseService.addUsersToExpense(expenseId, "ramesh@gmail.com");
		expenseService.addUsersToExpense(expenseId, "subhasis@gmail.com");
	}

	private static void bifurcateExpense(UUID expenseId) throws ExpenseException {
		expenseService.addUserShare(expenseId, expenseService.getExpense(expenseId).getUserEmailId(), 600d);
		expenseService.addUserShare(expenseId, "ramesh@gmail.com", 600d);
		expenseService.addUserShare(expenseId, "subhasis@gmail.com", 600d);
	}

	private static void contributeToExpense(UUID expenseId, String emailId) throws ExpenseException {
		userService.contributeToExpense(expenseId, emailId, 600d);
	}
}
