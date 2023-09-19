package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Loan;


public interface LoanRepo extends JpaRepository<Loan,Long>{
	
	@Query("SELECT e FROM Loan e WHERE e.user.userId = :id")
	List<Loan> findByUserId(@Param("id") Long userId);
}
