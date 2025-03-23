package com.lending.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.lending.enums.StatusCode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class LoanRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private double amount;
	private StatusCode loanStatus;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	@ManyToOne
	private Customer customer;
}