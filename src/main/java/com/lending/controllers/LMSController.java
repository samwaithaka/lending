package com.lending.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lending.dto.LoanRequestDto;
import com.lending.dto.ResponseDto;
import com.lending.services.CBSService;
import com.lending.services.LoanRequestService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("lms/api")
public class LMSController {
	
	private CBSService cbsService;
	private LoanRequestService loanRequestService;
	
	@GetMapping("/customer/{customerNumber}")
	public ResponseDto subscribe(@PathVariable String customberNumber) {
		cbsService.getKYC(customberNumber);
		return null;
	}
	
	@PostMapping("/loan-request")
	public ResponseDto requestLoan(@RequestBody LoanRequestDto loanRequestDto) {
		loanRequestService.requestLoan(loanRequestDto);
		//StatusCode.APPROVED;
		return null;
	}
	
	@GetMapping("/loan-status/{customerNumber}")
	public ResponseDto checkLoanStatus(@PathVariable String customberNumber) {
		loanRequestService.checkLoanStatus(customberNumber);
		return null;
	}
}
