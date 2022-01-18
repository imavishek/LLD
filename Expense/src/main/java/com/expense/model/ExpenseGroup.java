package com.expense.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
public class ExpenseGroup {
	private final UUID groupId;
	private final Set<User> groupMembers;
	private final Map<String, UserShare> userContributions;

	public ExpenseGroup() {
		this.groupId = UUID.randomUUID();
		this.groupMembers = new HashSet<>();
		this.userContributions = new HashMap<>();
	}
}
