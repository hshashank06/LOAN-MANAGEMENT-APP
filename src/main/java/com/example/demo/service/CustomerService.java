package com.example.demo.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import jakarta.transaction.Transactional;



@Service
public class CustomerService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	LoanRepo loanRepo;
	
	Logger logger = Logger.getLogger(CustomerService.class.getName());
	
	
	public CustomerService(UserRepo userRepo,AdminRepo adminRepo,ItemRepo itemRepo,LoanRepo loanRepo) {
		this.userRepo = userRepo;
		this.adminRepo = adminRepo;
		this.itemRepo = itemRepo;
		this.loanRepo = loanRepo;
	}

	
	public Boolean validateUserService(Long userId,String password) {
		
		boolean userExists = userRepo.existsByUserIdAndUserPassword(userId, password);
		if(userExists) return true;
		else return false;
		
	}
	
	public Boolean validateAdminService(Long adminId, String password) {
		boolean adminExists = adminRepo.existsByAdminIdAndAdminPassword(adminId, password);
		if(adminExists) return true;
		else return false;
	}
	public Boolean checkAddNewUser(User user) {
		
		Boolean checkIfUserPresent = userRepo.findAll().stream().anyMatch(eachUser -> eachUser.getLastName().equals(user.getLastName()) && eachUser.getUserEmail().equals(user.getUserEmail()));
		logger.info(checkIfUserPresent);
		try {
			if(!checkIfUserPresent) 
			{
				
				userRepo.save(user);		
				return true;
			}
			
		else {
			logger.info("The User ALready Exists");
			return false;
	}
		}
			catch(Exception e) {
				logger.info("Could not Register the User");
				return false;
			}
		
	}
	
	public List<User> getAllUsers(){
		List<User> users = userRepo.findAll();
		return users;
	}
	
	public String getUsernameForGivenId(Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		return user.getFirstName() + user.getLastName();
		
	}
	
	@Transactional
	public Boolean deleteUserById(Long id) {
		userRepo.deleteById(id);
		if(!userRepo.existsById(id)) {
			return true;
		}
		else {
			return false;
		}
	}
		
	@Transactional
	public void updateFields(Long entityId,Map<String,Object> updates) {
		
			User user = userRepo.findById(entityId).orElse(null);
		
			if(user != null) {
				for(Map.Entry<String,Object> entry : updates.entrySet()) {
					String fieldName = entry.getKey();
					Object value = entry.getValue();
					
					
					if(fieldName.equals("firstName")) {
						user.setFirstName((String) value);
						
					}
					
					else if(fieldName.equals("lastName")) {
						user.setLastName((String) value);
					}
					else if(fieldName.equals("userAge")) {
						user.setUserAge((Integer) value);
					}
					else if(fieldName.equals("userEmail")) {
						user.setUserEmail((String) value);
					}
					else if(fieldName.equals("userdob")) {
						DateTimeFormatter datTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate date = LocalDate.parse((String) value, datTimeFormatter);
						user.setUserdob(date);
						
					}
					
				}
				userRepo.save(user);
				
			}
	}

	
}
