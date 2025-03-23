package com.lending.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lending.entities.Customer;
import com.lending.entities.LoanRequest;

public interface LoanRequestRepo extends JpaRepository<LoanRequest, Long>{
	LoanRequest findById(long id);
	List<LoanRequest> findAll();
	List<LoanRequest> findByCustomer(Customer customer);
}