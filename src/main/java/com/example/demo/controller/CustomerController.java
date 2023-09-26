package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Item;
import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.User;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import com.example.demo.security.JwtHelper;



@RestController
@RequestMapping(value = "/loanapp")
public class CustomerController {
	
	  @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;


	    @Autowired
	    private JwtHelper helper;
@Autowired
PasswordEncoder passwordEncoder;
	

	@Autowired
	CustomerService customerService;
  
	
	
	Logger logger = Logger.getLogger(CustomerController.class.getName());
	
	
	CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}
	
	
	@PostMapping("/validate/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateUsers(@RequestBody User user) {
		
		Boolean response = customerService.validateUserService(user.getUserId(), user.getUserPassword());
		if(response) return ResponseEntity.ok("TRUE");
		else return ResponseEntity.badRequest().body("FALSE");
	
	}
	
	@ResponseBody
	@PostMapping("/validate/admin")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> validateCustomers(@RequestBody Admin admin){
		Boolean response = customerService.validateAdminService(admin.getAdminId(), admin.getAdminPassword());
		if(response) return ResponseEntity.ok("TRUE");
		else return ResponseEntity.badRequest().body("FALSE");
	}
	
	@PostMapping("/register/user")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> registerNewUserIntoBackEnd(@RequestBody @Valid User user){
		
		Boolean result = customerService.checkAddNewUser(user);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).body("NEW USER REGISTERED");
		}
		else {
			
			return ResponseEntity.badRequest().body("NEW USER COULLD NOT BE ADDED");
			}
		
	}
	
	@GetMapping("/get/users")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<List<User>>  getAllCustomerData(){
		List<User> listOfUsers = customerService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(listOfUsers);
			
	}
	
	@ResponseBody
	@PostMapping("/users/delete")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<Boolean> deleteUserById(@RequestBody User user){
		Boolean result = customerService.deleteUserById(user.getUserId());
		if(result == true)
		return ResponseEntity.status(HttpStatus.OK).body(true);
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
	}
	
	@ResponseBody
	@PostMapping("/update/user/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<String> updateTheFields(@PathVariable Long id,@RequestBody Map<String, Object> updates){
		
		System.out.println("Hi");
			customerService.updateFields(id, updates);
			return ResponseEntity.ok("The Fields have been updated");
		
		
	}
	@GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
	
  

	  

	@PostMapping("/login")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:3000")     
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
             System.out.println("hi");
	        this.doAuthenticate(request.getEmail(), request.getPassword());
	      
	        System.out.println("authentication");
	        
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        System.out.println(userDetails);
	        String token = this.helper.generateToken(userDetails);

	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername()).build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
	 }
    private void doAuthenticate(String email, String password) {

       
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println(authenticationToken);
    	try {
        	
            manager.authenticate(authenticationToken);
            

        } catch (BadCredentialsException e) {
        	System.out.println(e);
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
  

			
	
	
	
	
	

	

