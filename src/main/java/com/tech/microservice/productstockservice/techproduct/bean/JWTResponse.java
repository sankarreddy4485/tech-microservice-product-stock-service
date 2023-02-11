package com.tech.microservice.productstockservice.techproduct.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {

	public String token;

	public JWTResponse(String token) {
		super();
		this.token = token;
	}
	
}
