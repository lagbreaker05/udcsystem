package com.example.udc.dto;

//Data transport obj
public class UserDto {

	private String username;
	private String password;
	private String role;
    private String fullName;
	public UserDto(String username, String password, String role, String fullName) {
		super();
		this.username = username;
		this.password = password;
		this.role = "USER";
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	

    
    
    
}
