package com.lending.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lending.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
	Customer findById(long id);
	Customer findByCustomerNumber(String customerNumber);
	List<Customer> findAll();
}