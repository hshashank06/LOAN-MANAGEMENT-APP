package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.LoanRepo;

import utils.IssueStatus;
import utils.LoanReturnValue;

@Service
public class LoanService {
	
	@Autowired
	LoanRepo loanRepo;
	
	
	
	public LoanService(LoanRepo loanRepo) {
		this.loanRepo = loanRepo;
		
	}
	
	public Boolean registerForLoan(Loan loan) {
		loan.setStatus(IssueStatus.NO);
		loanRepo.save(loan);
		return true;
		
	}

	
	public List<Loan> displayLoansForGivenId(Long userId) {
		List<Loan> loanList = loanRepo.findByUserId(userId);
		return loanList;
	}
	 public List<LoanReturnValue> displayAllLoans(){
		 List<Loan> loanList = loanRepo.findAll();
		 List<LoanReturnValue> loanReturn = new ArrayList<>();
		 LoanReturnValue loanReturnValue = new LoanReturnValue();
		 for(Loan loan:loanList) {
			 loanReturnValue.setLoan(loan);
			
			 User user= (User) loan.getUser();
			 loanReturnValue.setUser(user);
			 loanReturn.add(loanReturnValue);
		 }
		 return loanReturn;
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
