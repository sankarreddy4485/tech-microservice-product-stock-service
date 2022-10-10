package com.tech.microservice.productstockservice.techproduct.bean;

import java.math.BigDecimal;

public class ProductStockBean {
	
	private long id;
	private String productName;
	private BigDecimal productPrice;
	private String productAvailability;
	private double discountOffer;
	
	private int port;

	
	
	public ProductStockBean(long id, String productName, BigDecimal productPrice, String productAvailability,
			double discountOffer, int port) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productAvailability = productAvailability;
		this.discountOffer = discountOffer;
		this.port = port;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(String productAvailability) {
		this.productAvailability = productAvailability;
	}

	public double getDiscountOffer() {
		return discountOffer;
	}

	public void setDiscountOffer(double discountOffer) {
		this.discountOffer = discountOffer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ProductStockBean [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productAvailability=" + productAvailability + ", discountOffer=" + discountOffer + ", port=" + port
				+ "]";
	}
	
	
	

}
