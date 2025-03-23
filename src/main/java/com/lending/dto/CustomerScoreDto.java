package com.lending.dto;

import lombok.Data;

@Data
public class CustomerScoreDto {
	private int id;
	private String customerNumber;
	private int score;
	private int limitAmount;
	private String exclusion;
	private String exclusionReason;
}
