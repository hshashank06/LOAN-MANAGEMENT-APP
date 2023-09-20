package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;

import jakarta.validation.Valid;
import utils.IssueStatus;
import utils.LoanReturnValue;

@RestController
@RequestMapping(value = "/loanapp")
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@ResponseBody
	@PostMapping("/register/loan/{userId}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> registerForNewLoan(@PathVariable("userId") Long userId,@RequestBody @Valid Loan loan){
		
		loanService.registerForLoan(userId,loan);
		return ResponseEntity.ok("DONE");
	}
	
	@ResponseBody
	@GetMapping("/display/loan/userId/{userId}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<List<Loan>> displaLoansGivenUserId(@PathVariable("userId") Long userId){
		List<Loan> result = loanService.displayLoansForGivenId(userId);
		return ResponseEntity.ok(result);
	}
	
	@ResponseBody
	@PostMapping("/loan/issue")
	ResponseEntity<Boolean> issueLoan(@RequestBody Loan loan){
		Long loanId = loan.getLoanId();
		IssueStatus issueStatus = loan.getStatus();
		Boolean result = loanService.issueOrRejectLoan(loanId, issueStatus);
		return ResponseEntity.ok(result);
		
	}
	
	@ResponseBody
	@GetMapping("/display/loan/all")
	ResponseEntity<List<LoanReturnValue>> displayAllLoans(){
		List<LoanReturnValue> result = loanService.displayAllLoans();
		return ResponseEntity.ok(result);
	}
	
	@ResponseBody
	@PostMapping("/delete/loan/{loanId}")
	ResponseEntity<String> deleteLoan(@PathVariable("loanId") Long loanId){
		loanService.deleteLoan(loanId);
		return ResponseEntity.ok("REMOVED");
	}
	

}
