package com.example.demo.entity;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
		

@Entity
public class User implements UserDetails {
	
	@Column(name = "FirstName",nullable=false,columnDefinition="TEXT")
	@NotNull(message = "First Name cannot be null")
	@NotEmpty(message = "First Name cannot be empty")
	private String firstName;
	
	@NotNull(message = "Last Name cannot be null")
	@NotEmpty(message = "Last Name cannot be Empty")
	@Column(name = "LastName",nullable=false,columnDefinition="TEXT")
	private String lastName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "Email",nullable=false,columnDefinition="TEXT")
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	
	@Column(name = "Age",nullable=false)
	@Min(value = 18, message="User Age cannot be Less than 18 for Loan Account")
	private Integer userAge;
	
	
	@Column(name = "DateOfBirth",nullable=false)
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private LocalDate userdob;
	
	@Column(name = "Password",nullable=false,columnDefinition="TEXT")
	@NotEmpty(message = "Password cannot be empty")
	
	private String userPassword;
		
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Loan> loan;
	
//	@OneToOne(mappedBy = "user")
//	private Account account;
	
	
	
	public User() {
	super();
}
	public User(
		@NotNull(message = "First Name cannot be null") @NotEmpty(message = "First Name cannot be empty") String firstName,
		@NotNull(message = "Last Name cannot be null") @NotEmpty(message = "Last Name cannot be Empty") String lastName,
		Long userId,
		@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "Email cannot be empty") String userEmail,
		@Min(value = 18, message = "User Age cannot be Less than 18 for Loan Account") Integer userAge,
		LocalDate userdob, @NotEmpty(message = "Password cannot be empty") @Size(min = 8, max = 20) String userPassword,
		Set<Loan> loan) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.userId = userId;
	this.email = userEmail;
	this.userAge = userAge;
	this.userdob = userdob;
	this.userPassword = userPassword;
	this.loan = loan;
	
}
	
	
	
	
	public User(
			@NotNull(message = "First Name cannot be null") @NotEmpty(message = "First Name cannot be empty") String firstName,
			@NotNull(message = "Last Name cannot be null") @NotEmpty(message = "Last Name cannot be Empty") String lastName,
			Long userId,
			@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "Email cannot be empty") String email,
			@Min(value = 18, message = "User Age cannot be Less than 18 for Loan Account") Integer userAge,
			LocalDate userdob, @NotEmpty(message = "Password cannot be empty") String userPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.email = email;
		this.userAge = userAge;
		this.userdob = userdob;
		this.userPassword = userPassword;
	}
	public Set<Loan> getLoan() {
		return loan;
	}
	public void setLoan(Set<Loan> loan) {
		this.loan = loan;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return email;
	}
	public void setUserEmail(String userEmail) {
		this.email = userEmail;
	}
	public Integer getUserAge() {	
		return Period.between(userdob,LocalDate.now()).getYears();
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public LocalDate getUserdob() {
		return userdob;
	}
	public void setUserdob(LocalDate userdob) {
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
				+ email + ", userAge=" + userAge + ", userdob=" + userdob + ", userPassword=" + userPassword + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPassword;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
