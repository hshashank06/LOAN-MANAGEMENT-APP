//package com.example.demo.entity;
//
//import java.time.LocalDate;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//import jakarta.validation.constraints.Min;
//
//@Entity
//public class Account {
//
//	@OneToOne
//	@JoinColumn(name = "userId", referencedColumnName = "userId")
//	private User user;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long accountNumber;
//	
//	@Min(value = 20000)
//	@Column(name = "Balance Amount")
//	private Integer accountBalance;
//	
//	@Column(name = "Account Type",columnDefinition = "TEXT")
//	private String accountType;
//	private LocalDate accountOpenDate;
//	private LocalDate accountCloseDate;
//	private Integer accountValidity;
//	
//}
