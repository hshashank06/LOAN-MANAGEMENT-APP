package com.example.demo.entity;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtRequest {
private String email;
private String password;

public void setEmail(String email) {
	this.email = email;
}


public void setPassword(String password) {
	this.password = password;
}


public JwtRequest(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}


public JwtRequest() {
	// TODO Auto-generated constructor stub
}


public String getEmail() {
	// TODO Auto-generated method stub
	return email;
}
public String getPassword() {
	// TODO Auto-generated method stub
	return password;
}

}
