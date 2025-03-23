package com.lending.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.lending.enums.Gender;
import com.lending.enums.IdType;
import com.lending.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String customerNumber;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private Gender gender;
	private IdType idType;
	private String idNumber;
	private LocalDateTime dob;
	private String createdAt;
	private LocalDateTime createdDate;
	private LocalDateTime updatedAt;
	private String mobile;
	private double monthlyIncome;
	private Status status;
}
