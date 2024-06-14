package com.pro.springPlayers.models;


public class Auth {
	
	private String username;
	
	private String Password;
	
	Auth(){}
	

	public Auth(String username, String password) {
		super();
		this.username = username;
		Password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
