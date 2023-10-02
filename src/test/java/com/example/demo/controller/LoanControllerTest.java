package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import junit.framework.Assert;
import utils.IssueStatus;
import utils.LoanType;


@WebMvcTest
@RunWith(SpringRunner.class)
public class LoanControllerTest {
	
	@Autowired
	@InjectMocks
	private LoanController loanController;
	
	@MockBean
	@Autowired
	private LoanService loanService;

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private AdminRepo adminRepo;
	@MockBean
	private ItemRepo itemRepo;
	@MockBean
	private UserRepo userRepo;
	@MockBean
	private LoanRepo loanRepo;
	
	@Test
	public void validateLoanApply() throws Exception {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		
		when(loanService.registerForLoan(loan,user.getUserId())).thenReturn(true);	
		ResponseEntity<String> response  = loanController.registerForNewLoan(user.getUserId(), loan);
		verify(loanService,times(1)).registerForLoan(loan,user.getUserId());
		String result = response.getBody();
		
		HttpStatusCode status = response.getStatusCode();
		
		Assert.assertEquals(result, "NEW Loan REGISTERED");
		Assert.assertEquals(status, HttpStatus.OK);
		
		
	}
	
	@Test
	public void testDeleteLoans() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		
		when(loanService.deleteLoan(loan.getLoanId())).thenReturn(true);
		loanController.deleteLoan(loan.getLoanId());
		verify(loanService,times(1)).deleteLoan(loan.getLoanId());
		ResponseEntity<String> response = loanController.deleteLoan(loan.getLoanId());
		Assert.assertEquals("REMOVED", response.getBody());
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
	

}