package com.example.demo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ItemController;
import com.example.demo.entity.Item;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.ItemService;

import junit.framework.Assert;
import utils.IssueStatus;
import utils.ItemMake;
import utils.LoanType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTest {
	
	@InjectMocks
	@Autowired
	private ItemController itemController;
	
	@MockBean
	@Autowired
	private ItemService itemService;

	
	@MockBean
	private AdminRepo adminRepo;
	@MockBean
	private ItemRepo itemRepo;
	@MockBean
	private UserRepo userRepo;
	@MockBean
	private LoanRepo loanRepo;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRegisterItem() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		Item item = new Item(1L,"MARUTI-CAR",IssueStatus.YES,ItemMake.Steel,LoanType.CAR,2000,loan);
		
		when(itemService.registerItem(loan.getLoanId(), item)).thenReturn(true);
		ResponseEntity<String> result  = itemController.itemRegister(loan.getLoanId(), item);
		verify(itemService,times(1)).registerItem(loan.getLoanId(), item);
		Assert.assertEquals(result.getBody(), "True");
		Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testItemDelete() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Loan loan = new Loan(1L,LoanType.ACCIDENT,3,IssueStatus.YES,user);
		Item item = new Item(1L,"MARUTI-CAR",IssueStatus.YES,ItemMake.Steel,LoanType.CAR,2000,loan);
		
		when(itemService.deleteItems(item.getItemId())).thenReturn(true);
		ResponseEntity<String> result = itemController.deleteItem(item);
		verify(itemService,times(1)).deleteItems(item.getItemId());
		Assert.assertEquals(result.getBody(), "TRUE");
		Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

}
