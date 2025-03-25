package com.lending.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lending.dto.LoanRequestDto;
import com.lending.dto.ResponseDto;
import com.lending.entities.Customer;
import com.lending.enums.StatusCode;
import com.lending.services.CBSService;
import com.lending.services.LoanRequestService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("lms/api")
public class LMSController {
	
	private CBSService cbsService;
	private LoanRequestService loanService;
	
	@GetMapping("/customer/{customerNumber}")
	public ResponseDto subscribe(@PathVariable String customberNumber) {
		Customer customer = cbsService.getKYC(customberNumber);
		ResponseDto responseDto = new ResponseDto();
		if(customer != null) {
			responseDto.setCustomerName(customer.getFirstName());
			responseDto.setStatusCode(StatusCode.VALID);
			responseDto.setStatusMessage("Valid customer");
		}
		return responseDto;
	}
	
	@PostMapping("/loan-request")
	public ResponseDto requestLoan(@RequestBody LoanRequestDto loanRequestDto) {
		loanService.requestLoan(loanRequestDto);
		//StatusCode.APPROVED;
		return null;
	}
	
	@GetMapping("/loan-status/{customerNumber}")
	public ResponseDto checkLoanStatus(@PathVariable String customberNumber) {
		loanService.checkLoanStatus(customberNumber);
		return null;
	}
}
