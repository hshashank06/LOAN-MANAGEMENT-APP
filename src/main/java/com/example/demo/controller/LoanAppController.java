package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	LoanAppService loanAppService;
	
	@Autowired
	LoanAppController(LoanAppService loanAppService){
		this.loanAppService = loanAppService;
	}
	
	@PostMapping("/validate")
	@ResponseBody
	ResponseEntity<String> validateUser(@Valid @RequestBody User user) {
		Boolean status = loanAppService.validateUserService(user.getUserId(), user.getUserPassword());
		if(status) {
			return ResponseEntity.status(HttpStatus.OK).body("VERIFIED");
		}
		else {
			return ResponseEntity.badRequest().body("NOT VERIFIED");
		}
	}
	
	
}
