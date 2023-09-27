package com.example.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.CustomerController;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import junit.framework.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {
	
	
	
	@InjectMocks
	@Autowired
	private CustomerService customerService;
	
	
	
	@MockBean
	private AdminRepo adminRepo;
	@MockBean
	private ItemRepo itemRepo;
	@MockBean
	private UserRepo userRepo;
	@MockBean
	private LoanRepo loanRepo;
	
	
	@Test
	public void testRegistrationService() {
		
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		when(userRepo.save(user)).thenReturn(user);
		
		Boolean response  = customerService.checkAddNewUser(user);
		verify(userRepo,times(1)).save(user);
		Assert.assertTrue(response);
		
	
	}
	
	@Test
	public void testDeleteService() {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		Mockito.when(userRepo.findById(user.getUserId())).thenReturn(java.util.Optional.empty());
		Boolean response = customerService.deleteUserById(user.getUserId());
		verify(userRepo,times(1)).deleteById(user.getUserId());
		Assert.assertTrue(response);
	}
	
	

}
