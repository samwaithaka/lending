package com.lending.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.lending.dto.CustomerScoreDto;
import com.lending.dto.LoanRequestDto;
import com.lending.entities.Customer;
import com.lending.entities.LoanRequest;
import com.lending.enums.StatusCode;
import com.lending.exceptions.LoanLimitException;
import com.lending.exceptions.ScoreThresholdException;
import com.lending.repo.LoanRequestRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoanRequestService {

	@Value("${app.constants.score-threashold}")
	private int scoreThreashold;
	private CBSService cbsService;
	private LoanRequestRepo loanRequestRepo;
	private ScoringEngineService scoringEngineService;
	/**
	 * The method initiates loan request; the following are the steps:
	 * 1. Checks if the customer is subscribed
	 * 2. If subscribed, the score is checked
	 * 3. Once the score if confirmed, loan is approved
	 * 
	 * @param customerNumber
	 * @return
	 */
	public StatusCode requestLoan(LoanRequestDto loanRequestDto) {
		String customerNumber = loanRequestDto.getCustomerNumber();
		Customer customer = cbsService.findExistingCustomer(customerNumber);
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setAmount(loanRequestDto.getAmount());
		loanRequest.setDateCreated(LocalDateTime.now());
		loanRequest.setCustomer(customer);
		loanRequest = loanRequestRepo.save(loanRequest);
		StatusCode statusCode = StatusCode.INVALID;
		if(customer == null)
			statusCode = StatusCode.INVALID;
		
		CustomerScoreDto scoreData = scoringEngineService.getScore(customerNumber);
		if(scoreData.getScore() < scoreThreashold) {
			statusCode = StatusCode.DECLINED;
			throw new ScoreThresholdException("Customers score does not meet the threshold");
		}
		
		if(scoreData.getLimitAmount() < loanRequestDto.getAmount()) {
			statusCode = StatusCode.DECLINED;
			throw new LoanLimitException("Customers does not qualify for the loan amount");
		}
		statusCode = StatusCode.APPROVED;
		loanRequest.setLoanStatus(statusCode);
		loanRequestRepo.save(loanRequest);
		return statusCode;
	}
	
	/**
	 * The method checks the loan status for the latest LoanRequest
	 * 
	 * @param customerNumber
	 * @return StatusCode
	 */
	public StatusCode checkLoanStatus(String customerNumber) {
		Customer customer = cbsService.findExistingCustomer(customerNumber);
		List<LoanRequest> loanRequests = loanRequestRepo.findByCustomer(customer);
		LoanRequest loanRequest = loanRequests.get(loanRequests.size() - 1);
		return loanRequest.getLoanStatus();
	}
}