package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.LoanRepo;

@Service
public class LoanAppService {
	
	LoanRepo loanRepo;

	public Boolean validateUserService(String userId,String password) {
		boolean userExists = loanRepo.existsByUserIdAndUserPassword(userId, password);
		return userExists;
	}
}
