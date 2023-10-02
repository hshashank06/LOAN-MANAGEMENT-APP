package com.example.demo.service;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import com.example.demo.entity.Item;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import utils.IssueStatus;
import utils.ItemMake;
import utils.LoanType;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest {
	
	@InjectMocks
	@Autowired
	ItemService itemService;
	

	@MockBean
	private AdminRepo adminRepo;
	@MockBean
	private ItemRepo itemRepo;
	@MockBean
	private UserRepo userRepo;
	@MockBean
	private LoanRepo loanRepo;

	
	@Test
	public void testItemRegistration() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		Item item = new Item(1L,"MARUTI-CAR",IssueStatus.YES,ItemMake.Steel,LoanType.CAR,2000,loan);
		Mockito.when(itemRepo.save(item)).thenReturn(item);
		Mockito.when(loanRepo.findById(loan.getLoanId())).thenReturn(Optional.of(loan));
		Boolean result  = itemService.registerItem(loan.getLoanId(), item);
		verify(itemRepo,times(1)).save(item);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testItemDeletion() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		Item item = new Item(1L,"MARUTI-CAR",IssueStatus.YES,ItemMake.Steel,LoanType.CAR,2000,loan);
		Boolean result  = itemService.deleteItems(item.getItemId());
		verify(itemRepo,times(1)).deleteById(item.getItemId());
		Assert.assertTrue(result);
		
	}
}