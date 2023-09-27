package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JwtHelper;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@InjectMocks
	@Autowired
	private CustomerController customerController;
	
	@MockBean
	@Autowired
	private CustomerService customerService;
	
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
	public void validateSuccessfulLUserRegistration() throws Exception {
		
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String jsonRequest = objectMapper.writeValueAsString(user);
		
		when(customerService.checkAddNewUser(Mockito.any(User.class))).thenReturn(true);
		mockMvc.perform(post("/loanapp/register/user").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk()).andExpect(content().string("NEW USER REGISTERED"));
		
		
	}
	
	@Test
	public void validateSuccessfullUserDeletion() throws Exception {
		User user = new User("Shashank","Hara",1L,"xyzpro@gmail.com",22,LocalDate.of(2005, 05, 05),"Milo@12345");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		when(customerService.deleteUserById(user.getUserId())).thenReturn(true);
		String jsonRequest = objectMapper.writeValueAsString(user);
		ResponseEntity<Boolean> result  = customerController.deleteUserById(user);
		Boolean response = result.getBody();
		Assert.assertTrue(response);
	}
	}
	
