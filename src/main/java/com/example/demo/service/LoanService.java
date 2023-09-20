package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import utils.IssueStatus;
import utils.LoanReturnValue;

@Service
public class LoanService {
	
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	UserRepo userRepo;
	
	
	
	public LoanService(LoanRepo loanRepo) {
		this.loanRepo = loanRepo;
		
	}
	

	public Boolean registerForLoan(Loan loan,Long userId) {
		loan.setStatus(IssueStatus.NO);
		User user=userRepo.findById(userId).orElse(null);
      loan.setUser(user);
	loanRepo.save(loan);
		if(userRepo.existsByUserId(user.getUserId())) {
			return true;

	}else {
			return false;
		}
	
		
	}
    public Boolean updateLoan(Long loanId, Loan updatedLoan) {
    	Optional<Loan> existingLoanOptional=loanRepo.findById(loanId);
    	if(existingLoanOptional.isPresent()) {
    		Loan existingLoan=existingLoanOptional.get();
    		existingLoan.setItem(updatedLoan.getItem());
    		existingLoan.setLoanDuration(updatedLoan.getLoanDuration());
    		existingLoan.setLoanType(updatedLoan.getLoanType());
    		existingLoan.setStatus(updatedLoan.getStatus());
    		loanRepo.save(existingLoan);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
	
	public List<Loan> displayLoansForGivenId(Long userId) {
		List<Loan> loanList = loanRepo.findByUserId(userId);
		return loanList;
	}
	 public List<LoanReturnValue> displayAllLoans(){
		 List<Loan> loanList = loanRepo.findAll();
		 List<LoanReturnValue> loanReturn = new ArrayList<>();
		
		 for(Loan loan:loanList) {
			 LoanReturnValue loanReturnValue = new LoanReturnValue();
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
