package com.antoniocmoura.cloudparking.infrastructure.api.auth;

import lombok.Data;

@Data
public class AuthResponse {
	private String name;
	private String accessToken;

	public AuthResponse() { }
	
	public AuthResponse(String name, String accessToken) {
		this.name = name;
		this.accessToken = accessToken;
	}

}
