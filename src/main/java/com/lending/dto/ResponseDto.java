package com.lending.dto;

import com.lending.enums.StatusCode;

import lombok.Data;

@Data
public class ResponseDto {
	private StatusCode statusCode;
	private String statusMessage;
	private String customerName;
}