package com.tech.microservice.productstockservice.techproduct.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
    private String secretKey;
		
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims,userDetails.getUsername());
		
	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		String token = Jwts.builder().setClaims(claims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		
				return token;
				
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		String userName = getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		Date expiryDate = getExpiryDateFromToken(token); 
		return expiryDate.before(new Date());
	}

	private Date getExpiryDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		Claims claims =getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String getUserNameFromToken(String token) {
		// TODO Auto-generated method stub
		return getClaimFromToken(token,Claims::getSubject);
	}
	

}
