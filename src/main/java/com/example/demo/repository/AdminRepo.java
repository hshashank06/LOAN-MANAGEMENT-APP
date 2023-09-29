package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;


public interface AdminRepo extends JpaRepository<Admin,Long> {

	boolean existsByAdminIdAndAdminPassword(Long adminId,String adminPassword);
	public Optional<Admin> findByEmail(String email);
	
}
