package com.example.demo.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "orders")
public class OrderDTO {

	@Id
	private String order_number;
	
	private String status;
	@DateTimeFormat(style = "M-") 
	@CreatedDate
	private Date date;
	
	private String customer;
	
	private TaxesAmountsDTO taxes_amounts ;

	private  double taxes_total;
	
	private  double total_amount;

	private List<OrderItemDTO> listOrdersItems;
	
	
	public double getTaxes_total() {
		return taxes_total;
	}

	public void setTaxes_total(double taxes_total) {
		this.taxes_total = taxes_total;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public TaxesAmountsDTO getTaxes_amounts() {
		return taxes_amounts;
	}

	public void setTaxes_amounts(TaxesAmountsDTO taxes_amounts) {
		this.taxes_amounts = taxes_amounts;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public List<OrderItemDTO> getListOrdersItems() {
		return listOrdersItems;
	}

	public void setListOrdersItems(List<OrderItemDTO> listOrdersItems) {
		this.listOrdersItems = listOrdersItems;
	}
	
	

	
}
