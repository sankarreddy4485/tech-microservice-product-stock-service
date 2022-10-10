package com.tech.microservice.productstockservice.techproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tech.microservice.productstockservice.techproduct.bean.ProductStockBean;
import com.tech.microservice.productstockservice.techproduct.entity.ProductStock;
import com.tech.microservice.productstockservice.techproduct.entity.ProductStockRepository;


@RestController
public class ProductStockController {
	@Autowired
	Environment environment;
	@Autowired
	ProductStockRepository productStockRepository;
	
	@GetMapping("/check-product-stock/productName/{productName}/ProductAvailability/{productAvailability}")
	public ProductStockBean checkProductAvailabilty(@PathVariable String productName,@PathVariable String productAvailability) {

		ProductStock productStock = productStockRepository.findByProductNameAndProductAvailability(productName,
				productAvailability);
		ProductStockBean productStockBean = new ProductStockBean(productStock.getId(), productStock.getProductName(),
				productStock.getProductPrice(), productStock.getProductAvailability(), productStock.getDiscountOffer(),
				Integer.parseInt(environment.getProperty("local.server.port")));
		return productStockBean;

	}

}
