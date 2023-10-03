package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/loanapp")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	
	
	Logger logger = Logger.getLogger(CustomerController.class.getName());
	
	
	CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}
	
	@PostMapping("/user/{userId}/username")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> getUsernameForUserId(@PathVariable("userId") Long userId){
		String userName = customerService.getUsernameForGivenId(userId);
		return ResponseEntity.ok(userName);
	}
	
	
	@PostMapping("/validate/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateUsers(@RequestBody User user) {
		
		Boolean response = customerService.validateUserService(user.getUserId(), user.getUserPassword());
		if(response) {
			logger.info("SUCCESSFULLY VALIDATED THE USER");
			return ResponseEntity.ok("TRUE");
		}
		else {
			logger.info("COULD NOT VALIDATE THE USER");
			return ResponseEntity.badRequest().body("FALSE");
		}
	
	}
	
	@ResponseBody
	@PostMapping("/validate/admin")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateCustomers(@RequestBody Admin admin){
		Boolean response = customerService.validateAdminService(admin.getAdminId(), admin.getAdminPassword());
		if(response) return ResponseEntity.ok("TRUE");
		else return ResponseEntity.badRequest().body("FALSE");
	}
	
	@PostMapping("/register/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> registerNewUserIntoBackEnd(@RequestBody @Valid User user){
		Boolean result = customerService.checkAddNewUser(user);
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body("NEW USER REGISTERED");
		}
		else {
			
			return ResponseEntity.badRequest().body("NEW USER COULLD NOT BE ADDED");
			}
		
	}
	
	@GetMapping("/get/users")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<List<User>>  getAllCustomerData(){
		List<User> listOfUsers = customerService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
			
	}
	
	@ResponseBody
	@PostMapping("/users/delete")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Boolean> deleteUserById(@RequestBody User user){
		Boolean result = customerService.deleteUserById(user.getUserId());
		if(result) return ResponseEntity.status(HttpStatus.OK).body(true);
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	@ResponseBody
	@PostMapping("/update/user/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> updateTheFields(@PathVariable Long id,@RequestBody Map<String, Object> updates){
		
			customerService.updateFields(id, updates);
			return ResponseEntity.ok("The Fields have been updated");
		
		
	}
			
	}
	
	
	
	

	

