package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.LoanRepo;

@Service
public class LoanAppService {
	
	@Autowired
	LoanRepo loanRepo;
	
	
	public LoanAppService(LoanRepo loanRepo) {
		this.loanRepo = loanRepo;
	}
	public Boolean validateUserService(Long userId,String password) {
		boolean userExists = loanRepo.existsByUserIdAndUserPassword(userId, password);
		return userExists;
	}
	
	public Boolean checkAddNewUser(User user) {
		loanRepo.save(user);
		if(loanRepo.existsByUserId(user.getUserId())) {
			return true;

	}else {
			return false;
		}
	}
}
