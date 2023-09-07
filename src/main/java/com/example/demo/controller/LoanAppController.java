package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.LoanAppService;

@RestController
@RequestMapping(value = "/loanapp")
public class LoanAppController {
	
	LoanAppService loanAppService;
	
	@Autowired
	LoanAppController(LoanAppService loanAppService){
		this.loanAppService = loanAppService;
	}
	
	@PostMapping("/validate")
	public String validateUser(@RequestBody User user) {
		Boolean status = loanAppService.validateUserService(user.getUserId(), user.getUserPassword());
		if(status) {
			return "VERY GOOD";
		}
		return null;
	}
	
	
}
