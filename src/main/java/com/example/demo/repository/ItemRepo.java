package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Item;


public interface ItemRepo extends JpaRepository<Item,Long> {

	@Query("SELECT e FROM Item e WHERE e.loan.loanId = :id")
	List<Item> findByLoanId(@Param("id") Long id);
	
	

	
}
