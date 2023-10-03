package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import utils.IssueStatus;
import utils.LoanReturnValue;
import utils.LoanType;

@Service
public class LoanService {
	
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	UserRepo userRepo;
	
	Logger logger = Logger.getLogger(LoanService.class.getName());
	
	
	public LoanService(LoanRepo loanRepo) {
		this.loanRepo = loanRepo;
		
	}
	

	public Boolean registerForLoan(Loan loan,Long userId) {
		try {
			loan.setStatus(IssueStatus.PENDING);
			User user=userRepo.findById(userId).orElse(null);
		    loan.setUser(user);
			loanRepo.save(loan);
			return true;
			}
			catch(Exception e) {
				logger.info("Could not register for a new loan");
				return false;
			}
	
		
	}
    public Boolean updateLoan(Long loanId, Map<String,Object> fields) {
    	Loan loan = loanRepo.findById(loanId).orElse(null);
    	if(loan != null) {
    		for(Map.Entry<String, Object> eachItem : fields.entrySet()) {
    			String field = eachItem.getKey();
				Object value = eachItem.getValue();
				if(field.equals("loanType")) {
					LoanType loanType = LoanType.valueOf((String) value);
					loan.setLoanType(loanType);
				}
				else if(field.equals("loanDuration")) {
					loan.setLoanDuration((Integer) value);
				}
    		}
    		loanRepo.save(loan);   		
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
