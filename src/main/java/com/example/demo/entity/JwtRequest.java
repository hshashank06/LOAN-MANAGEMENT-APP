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
public String getEmail() {
	// TODO Auto-generated method stub
	return email;
}
public String getPassword() {
	// TODO Auto-generated method stub
	return password;
}

}
