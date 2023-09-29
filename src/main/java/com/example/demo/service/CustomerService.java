package com.example.demo.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.LoanRepo;
import com.example.demo.repository.UserRepo;

import jakarta.transaction.Transactional;


@Configuration
@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	LoanRepo loanRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
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
	
	
	public Boolean checkAddNewAdmin(Admin admin) {
		try {
			admin.setAdminPassword(passwordEncoder.encode(admin.getPassword()));
			adminRepo.save(admin);
			
			return true;
		}
		
		catch(Exception e) {
			logger.info("Could not Register A new Admin");
			return false;
		}
	}
	
	public Boolean checkAddNewUser(User user) {
		try {
		user.setUserPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		
		return true;
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
						user.setUserdob((LocalDate) value);
					}
					
				}
				userRepo.save(user);
				
			}
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username.contains("loanapplication")) {
			Admin admin = adminRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("Admin cannot be found"));
			System.out.println(admin);
			return admin;
		}
		else {
				
			User user=userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not Found!!"));
		
			return user;
	}
	}
	

	
}
