package com.tech.microservice.productstockservice.techproduct.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.microservice.productstockservice.techproduct.bean.JWTRequest;
import com.tech.microservice.productstockservice.techproduct.bean.JWTResponse;
import com.tech.microservice.productstockservice.techproduct.bean.ProductStockBean;
import com.tech.microservice.productstockservice.techproduct.entity.ProductStock;
import com.tech.microservice.productstockservice.techproduct.entity.ProductStockRepository;
import com.tech.microservice.productstockservice.techproduct.service.UserService;
import com.tech.microservice.productstockservice.techproduct.utility.JWTUtility;


@RestController
public class ProductStockController {
	@Autowired
	Environment environment;
	@Autowired
	ProductStockRepository productStockRepository;
	@Autowired
	JWTUtility jwtUtility;
	@Autowired
	private AuthenticationManager authenticatonManager;
	@Autowired
	private UserService userService;

	@GetMapping("/check-product-stock/productName/{productName}/ProductAvailability/{productAvailability}")
	public ProductStockBean checkProductAvailabilty(@PathVariable String productName,@PathVariable String productAvailability) throws Exception {

		ProductStock productStock = productStockRepository.findByProductNameAndProductAvailability(productName,
				productAvailability);
		System.out.println("PRoductstock>>>>>"+productStock.getProductName());
		ProductStockBean productStockBean = new ProductStockBean(productStock.getId(), productStock.getProductName(),
				productStock.getProductPrice(), productStock.getProductAvailability(), productStock.getDiscountOffer(),
				Integer.parseInt(environment.getProperty("local.server.port")));
		return productStockBean;

	}

	@PostMapping("/authenticate")
	public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws ArrayIndexOutOfBoundsException {
		
		try {
				authenticatonManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			//throw new Exception("Invalid Credentials",e);
		}  
		UserDetails userDetails =userService.loadUserByUsername(jwtRequest.getUserName());
		
		String token=jwtUtility.generateToken(userDetails);
		System.out.println("token>>>>>>>>>>>"+token);
		
		return new JWTResponse(token);
		
	}
}
