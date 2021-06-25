package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "orderItem")
public class OrderItemDTO {
	
	private String name;
	
	private int quantity;
	
	private double unit_price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	

}
