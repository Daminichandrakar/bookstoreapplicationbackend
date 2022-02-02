package com.bridgelabz.bookstore.dto;

import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString public class UserDto {
	
	@NotNull
	@Pattern(regexp = "^[A-Z][a-z]{3,}$", message= "Name Not In Proper Format")
	public String fullName;
	
	@NotNull
	public String emailId;
	
	@NotNull
	public String password;
	
	@NotNull
	public Long phoneNumber;
	
}