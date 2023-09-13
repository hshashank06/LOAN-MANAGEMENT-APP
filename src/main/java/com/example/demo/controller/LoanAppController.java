package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.service.LoanAppService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import utils.Role;

@RestController
@RequestMapping(value = "/loanapp")
public class LoanAppController {
	
	@Autowired
	LoanAppService loanAppService;
	
	Logger logger = Logger.getLogger(LoanAppController.class.getName());
	
	
	LoanAppController(LoanAppService loanAppService){
		this.loanAppService = loanAppService;
	}
	
	@PostMapping("/validate/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateUsers(@RequestBody User user) {
		
		Boolean response = loanAppService.validateUserService(user.getUserId(), user.getUserPassword());
		if(response) return ResponseEntity.ok("TRUE");
		else return ResponseEntity.badRequest().body("FALSE");
	
	}
	
	ResponseEntity<String> validateCustomers(@RequestBody Admin admin){
		Boolean response = loanAppService.validateAdminService(admin.getAdminId(), admin.getAdminPassword());
		if(response) return ResponseEntity.ok("TRUE");
		else return ResponseEntity.badRequest().body("FALSE");
	}
	
	@PostMapping("/register/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> registerNewUserIntoBackEnd(@RequestBody @Valid User user){
		Boolean result = loanAppService.checkAddNewUser(user);
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
		List<User> listOfUsers = loanAppService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
			
	}
	
	@ResponseBody
	@PostMapping("/users/delete")
	ResponseEntity<Boolean> deleteUserById(@RequestBody User user){
		Boolean result = loanAppService.deleteUserById(user.getUserId());
		if(result == true)
		return ResponseEntity.status(HttpStatus.OK).body(true);
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	@ResponseBody
	@PostMapping("/update/user/{id}")
	ResponseEntity<String> updateTheFields(@PathVariable Long id,@RequestBody Map<String, Object> updates){
		
		try {
			loanAppService.updateFields(id, updates);
			return ResponseEntity.ok("The Fields have been updated");
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body("The fields could not be updated");
		}
		
	}
		
		
	}
	
	
	
	

	

