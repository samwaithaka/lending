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
		ResponseDto response = new ResponseDto();
		if(customer != null) {
			response.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
			response.setStatusCode(StatusCode.VALID);
			response.setStatusMessage("Customer successfully subscribed");
		} else {
			response.setStatusCode(StatusCode.INVALID);
			response.setStatusMessage("Customer number could not be validated");
		}
		return response;
	}
	
	@PostMapping("/loan-request")
	public ResponseDto requestLoan(@RequestBody LoanRequestDto loanRequestDto) {
		StatusCode statusCode = loanRequestService.requestLoan(loanRequestDto);
		ResponseDto response = new ResponseDto();
		response.setStatusCode(statusCode);
		if(statusCode == StatusCode.APPROVED) {
			response.setStatusMessage("Loan request approved");
		} else {
			response.setStatusMessage("Loan request approved");
		}
		return response;
	}
	
	@GetMapping("/loan-status/{customerNumber}")
	public ResponseDto checkLoanStatus(@PathVariable String customberNumber) {
		StatusCode statusCode = loanRequestService.checkLoanStatus(customberNumber);
		ResponseDto response = new ResponseDto();
		response.setStatusCode(statusCode);
		if(statusCode == StatusCode.APPROVED) {
			response.setStatusMessage("Loan request approved");
		} else {
			response.setStatusMessage("Loan not approved");
		}
		return response;
	}
}
