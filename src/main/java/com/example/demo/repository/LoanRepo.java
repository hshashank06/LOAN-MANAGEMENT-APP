package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface LoanRepo extends JpaRepository<User,String>{
	
	boolean existsByUserIdAndUserPassword(String userId,String userPassword);
}
