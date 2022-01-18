package com.expense.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class User {
	private String emailId;
	private String name;
	private String phoneNumber;
}
