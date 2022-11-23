package com.capgemini.sprint1.Sprint1.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
	
	private long userId;
	
	private String password;
	
	private boolean isAdmin;
	
	private boolean isClient;
	
	private boolean isEngineer;
	
	
	public UserLogin(long userId,String password) {
		this.userId = userId;
		this.password = password;
		this.isAdmin = false;
		this.isClient = false;
		this.isEngineer = false;
	}

}
