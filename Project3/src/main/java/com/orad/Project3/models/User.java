package com.orad.Project3.models;

public class User {

	private String clientType, email, password, token;

	// ctor

	public User() {
		super();
	}

	public User(String clientType, String email, String password, String token) {
		super();
		this.clientType = clientType;
		this.email = email;
		this.password = password;
		this.token = token;
	}

	// get-set

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [clientType=" + clientType + ", email=" + email + ", password=" + password + ", token=" + token
				+ "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
