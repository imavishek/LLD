package com.expense.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class Contribution {
	private UUID contributionId;
	private Double contributionAmount;
	private String transactionId;
	private LocalDateTime contributionDate;
}
