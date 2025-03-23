package com.lending.dto;

import lombok.Data;

@Data
public class LoanRequestDto {
	private String customerNumber;
	private double amount;
}