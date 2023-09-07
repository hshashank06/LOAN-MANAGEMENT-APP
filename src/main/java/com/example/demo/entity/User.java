package com.example.demo.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class User {
	
	@Column(name = "FirstName",nullable=false,columnDefinition="TEXT")
	private String firstName;
	@Column(name = "LatName",nullable=false,columnDefinition="TEXT")
	private String lastName;
	@Id
	private String userId;
	@Column(name = "Email",nullable=false,columnDefinition="TEXT")
	private String userEmail;
	@Column(name = "Age",nullable=false,columnDefinition="TEXT",unique=true)
	private String userAge;
	@Column(name = "DateOfBirth",nullable=false)
	private Date userdob;
	@Column(name = "Password",nullable=false,columnDefinition="TEXT")
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
