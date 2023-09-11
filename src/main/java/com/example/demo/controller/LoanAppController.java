package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.LoanAppService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/loanapp")
public class LoanAppController {
	
	@Autowired
	LoanAppService loanAppService;
	
	
	LoanAppController(LoanAppService loanAppService){
		this.loanAppService = loanAppService;
	}
	
	@PostMapping("/validate")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateUser(@RequestBody User user) {
		System.out.println("I am here");
		Boolean status = loanAppService.validateUserService(user.getUserId(), user.getUserPassword());
		if(status) {
			System.out.println("Verified");
			return ResponseEntity.status(HttpStatus.OK).body("VERIFIED");
			
		}
		else {
			return ResponseEntity.badRequest().body("NOT VERIFIED");
		}
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
			return ResponseEntity.badRequest().body("NEW USER COULLD NOT BE ADDED");		}
		
	}
	
}
