package com.example.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import utils.IssueStatus;
import utils.LoanType;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LoanServiceTest {

	@InjectMocks
	@Autowired
	private LoanService loanService;
	
	@MockBean
	private AdminRepo adminRepo;
	@MockBean
	private ItemRepo itemRepo;
	@MockBean
	private UserRepo userRepo;
	@MockBean
	private LoanRepo loanRepo;
	
	@Test
	public void testLoanRegistrationService() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		Mockito.when(loanRepo.save(loan)).thenReturn(loan);
		Mockito.when(userRepo.findById(user.getUserId())).thenReturn(Optional.of(user));
		
		Boolean result  = loanService.registerForLoan(loan,user.getUserId());
		
		verify(loanRepo,times(1)).save(loan);
		Assert.assertTrue(result);
		
		
	}
	
	@Test
	public void testLoanDeletionService() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		
		Boolean result  = loanService.deleteLoan(loan.getLoanId());
		Assert.assertTrue(result);
		
	}
}