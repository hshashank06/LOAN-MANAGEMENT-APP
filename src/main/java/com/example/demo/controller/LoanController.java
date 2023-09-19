package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;

import jakarta.validation.Valid;
import utils.IssueStatus;

@RestController
@RequestMapping(value = "/loanapp")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@ResponseBody
	@PostMapping("/register/{userId}/loan")
	ResponseEntity<String> registerForNewLoan(@PathVariable Long userId,@RequestBody @Valid Loan loan){
		
		Boolean result=loanService.registerForLoan(loan,userId);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body("NEW Loan REGISTERED");
		}
		else {
			
			return ResponseEntity.badRequest().body("NEW Loan COULLD NOT BE ADDED");
			}
	}
	
	@ResponseBody
	@GetMapping("/display/loan/userId/{userId}")
	ResponseEntity<List<Loan>> displaLoansGivenUserId(@PathVariable("userId") Long userId){
		List<Loan> result = loanService.displayLoansForGivenId(userId);
		return ResponseEntity.ok(result);
	}
	
	@ResponseBody
	@PostMapping("/loan/{loanId}/issue")
	ResponseEntity<Boolean> issueLoan(@PathVariable Long loanId,@RequestBody Loan loan){
		
		IssueStatus issueStatus = loan.getStatus();
		Boolean result = loanService.issueOrRejectLoan(loanId, issueStatus);
		return ResponseEntity.ok(result);
		
	}
	@ResponseBody
	@PutMapping("/loan/{loanId}/update")
	ResponseEntity<Boolean> updateLoan(@PathVariable Long loanId ,@RequestBody  @Valid Loan updatedLoan){
	  
		Boolean result = loanService.updateLoan(loanId, updatedLoan);
		return ResponseEntity.ok(result);
		
	}
	@ResponseBody
	@GetMapping("/display/loan/all")
	ResponseEntity<List<Loan>> displayAllLoans(){
		List<Loan> result = loanService.displayAllLoans();
		return ResponseEntity.ok(result);
	}
	
	@ResponseBody
	@PostMapping("/delete/loan/{loanId}")
	ResponseEntity<String> deleteLoan(@PathVariable("loanId") Long loanId){
		loanService.deleteLoan(loanId);
		return ResponseEntity.ok("REMOVED");
	}
	

}
