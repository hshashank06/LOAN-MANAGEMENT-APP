package com.example.demo.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
public class User {
	
	@Column(name = "FirstName",nullable=false,columnDefinition="TEXT")
	@NotNull(message = "First Name cannot be null")
	@NotEmpty(message = "First Name cannot be empty")
	private String firstName;
	@NotNull(message = "Last Name cannot be null")
	@NotEmpty(message = "Last Name cannot be Empty")
	@Column(name = "LastName",nullable=false,columnDefinition="TEXT")
	private String lastName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userId;
	@Column(name = "Email",nullable=false,columnDefinition="TEXT")
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email cannot be empty")
	private String userEmail;
	@Column(name = "Age",nullable=false,columnDefinition="TEXT",unique=true)
	@Min(value = 18, message="User Age cannot be Less than 18 for Loan Account")
	private String userAge;
	@NotEmpty(message = "Date cannot be empty")
	@Column(name = "DateOfBirth",nullable=false)
	private Date userdob;
	@Column(name = "Password",nullable=false,columnDefinition="TEXT")
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, max = 20)
	private String userPassword;
	
	public User(String firstName, String lastName, String userId, String userEmail, String userAge, Date userdob,
			String userPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.userEmail = userEmail;
		this.userAge = userAge;
		this.userdob = userdob;
		this.userPassword = userPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public Date getUserdob() {
		return userdob;
	}
	public void setUserdob(Date userdob) {
		this.userdob = userdob;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + ", userEmail="
				+ userEmail + ", userAge=" + userAge + ", userdob=" + userdob + ", userPassword=" + userPassword + "]";
	}
	
	
	
	
	
	


}
