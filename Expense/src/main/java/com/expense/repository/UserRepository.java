package com.expense.repository;

import com.expense.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
	private static final Map<String, User> userMap = new HashMap<>();

	public User save(User user) {
		userMap.putIfAbsent(user.getEmailId(), user);

		return user;
	}

	public User findByEmailId(String emailId) {
		return userMap.get(emailId);
	}
}
