package com.example.demo.utils;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.OrderDTO;
import com.example.demo.models.OrderItemDTO;

@Service
public class Utilities {

	
	public OrderDTO setedPricesOrder(OrderDTO orderDto) {
		
		//Segment functionality
		List<OrderItemDTO> orderItemsList = orderDto.getListOrdersItems();


		 
		double taxeAmountCity = orderDto.getTaxes_amounts().getCity_tax();
		double taxeAmountCountry = orderDto.getTaxes_amounts().getCountry_tax();
		double taxeAmountFederal = orderDto.getTaxes_amounts().getFederal_tax();
		double taxeAmountState = orderDto.getTaxes_amounts().getState_tax();
		double totalPriceOrderItems = 0.0;
		double total_amount = 0.0;
		 
		for (OrderItemDTO orderItemDTO : orderItemsList) {
			 totalPriceOrderItems = totalPriceOrderItems + (orderItemDTO.getQuantity() * orderItemDTO.getUnit_price());
		}
		
		 //Improve with a trigger in the db
		 double totalTaxes = (totalPriceOrderItems * taxeAmountCity)+ (totalPriceOrderItems * taxeAmountCountry)+
				 (totalPriceOrderItems * taxeAmountFederal)+(totalPriceOrderItems*taxeAmountState);
		 
		 total_amount = totalPriceOrderItems + totalTaxes;
		 
		 orderDto.setTaxes_total(totalTaxes);
		 orderDto.setTotal_amount(total_amount);
		 
		return orderDto;
	}
	 
	
}
