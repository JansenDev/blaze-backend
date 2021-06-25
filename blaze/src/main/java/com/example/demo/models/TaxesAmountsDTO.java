package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "taxesAmounts")
public class TaxesAmountsDTO {
	
	private double city_tax;
	
	private double country_tax;
	
	private double state_tax;
	
	private double federal_tax;

	public double getCity_tax() {
		return city_tax;
	}

	public void setCity_tax(double city_tax) {
		this.city_tax = city_tax;
	}

	public double getCountry_tax() {
		return country_tax;
	}

	public void setCountry_tax(double country_tax) {
		this.country_tax = country_tax;
	}

	public double getState_tax() {
		return state_tax;
	}

	public void setState_tax(double state_tax) {
		this.state_tax = state_tax;
	}

	public double getFederal_tax() {
		return federal_tax;
	}

	public void setFederal_tax(double federal_tax) {
		this.federal_tax = federal_tax;
	}


	
	

}
