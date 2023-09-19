package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepo;

import utils.IssueStatus;

@Service
public class LoanService {
	
	@Autowired
	LoanRepo loanRepo;
	
	public LoanService(LoanRepo loanRepo) {
		this.loanRepo = loanRepo;
	}
	
	public Boolean registerForLoan(Loan loan) {
		
		loanRepo.save(loan);
		return true;
		
	}

	
	public List<Loan> displayLoansForGivenId(Long userId) {
		List<Loan> loanList = loanRepo.findByUserId(3L);
		return loanList;
	}
	 public List<Loan> displayAllLoans(){
		 List<Loan> loanList = loanRepo.findAll();
		 return loanList;
	 }
	 
	 public Boolean issueOrRejectLoan(Long loanId,IssueStatus status) {
		 Loan loan = loanRepo.findById(loanId).orElse(null);
		 loan.setStatus(status);
		 loanRepo.save(loan);
		 return true;
		 
	 }
	 
	 public Boolean deleteLoan(Long loanId) {
		 loanRepo.deleteById(loanId);
		 return true;
	 }
	
	
}
