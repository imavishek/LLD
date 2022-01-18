package com.expense.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserShare {
	private final String userEmailId;
	private final Double amount;
	private final List<Contribution> contribution;

	public UserShare(String emailId, Double amount) {
		this.userEmailId = emailId;
		this.amount = amount;
		this.contribution = new ArrayList<>();
	}
}
