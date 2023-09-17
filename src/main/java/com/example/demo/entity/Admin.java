package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	@Column(name = "First Name",columnDefinition="text")
	@NotEmpty(message = "First Name cannot be Empty")
	private String firstName;
	@Column(name = "Last Name",columnDefinition="text")
	@NotEmpty(message = "Last Name cannot be Empty")
	private String lastName;
	@Column(name="Password",columnDefinition = "text")
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 6, max = 18,message ="The Password size must be within 6 to 18 characters")
	private String adminPassword;
	@Column(name="Age")
	@NotEmpty(message = "Age cannot be empty")
	@Size(min = 28, max = 59, message = "Age of admin cannot be more that 59 or less that 28")
	private Integer adminAge;
	@Column(name = "DOB")
	@NotEmpty(message = "Please Enter the DOB ")
	private LocalDate adminDOB;
	
	
	
	
	public Admin(Long adminId, @NotEmpty(message = "First Name cannot be Empty") String firstName,
			@NotEmpty(message = "Last Name cannot be Empty") String lastName,
			@NotEmpty(message = "Password cannot be empty") @Size(min = 6, max = 18, message = "The Password size must be within 6 to 18 characters") String adminPassword,
			@NotEmpty(message = "Age cannot be empty") @Size(min = 28, max = 59, message = "Age of admin cannot be more that 59 or less that 28") Integer adminAge,
			@NotEmpty(message = "Please Enter the DOB ") LocalDate adminDOB) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adminPassword = adminPassword;
		this.adminAge = adminAge;
		this.adminDOB = adminDOB;
		
	}
	
	
	
	
	



	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", adminPassword="
				+ adminPassword + ", adminAge=" + adminAge + ", adminDOB=" + adminDOB + "]";
	}


	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Integer getAdminAge() {
		return adminAge;
	}
	public void setAdminAge(Integer adminAge) {
		this.adminAge = adminAge;
	}
	public LocalDate getAdminDOB() {
		return adminDOB;
	}
	public void setAdminDOB(LocalDate adminDOB) {
		this.adminDOB = adminDOB;
	}
	
	
	

}
