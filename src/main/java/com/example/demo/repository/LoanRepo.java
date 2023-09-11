package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.User;

public interface LoanRepo extends JpaRepository<User,String>{
	
	boolean existsByUserIdAndUserPassword(Long userId,String userPassword);
	boolean existsByUserId(Long id);
}
