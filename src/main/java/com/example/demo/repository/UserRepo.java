package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;


public interface UserRepo extends JpaRepository<User,Long>{

	
	boolean existsByUserIdAndUserPassword(Long userId,String userPassword);
	boolean existsByUserId(Long id);
	void save(Optional<User> user);
}
