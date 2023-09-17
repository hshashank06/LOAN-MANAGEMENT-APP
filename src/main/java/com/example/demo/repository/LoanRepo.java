package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Loan;


public interface LoanRepo extends JpaRepository<Loan,Long>{
	
}
