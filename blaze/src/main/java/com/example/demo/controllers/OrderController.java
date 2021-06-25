package com.example.demo.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.OrderDTO;
import com.example.demo.models.OrderItemDTO;
import com.example.demo.repositories.IOrderDAO;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private IOrderDAO repoIOrders;

	@GetMapping
	public ResponseEntity<?> findAllOrders() {
		try {
			List<OrderDTO> getAllOrdersResponseBody = repoIOrders.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(getAllOrdersResponseBody);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'Error': 'Server Error' },{'Message': "+e.getMessage()+"}");
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrdersById(@PathVariable String id  ) {
		
		try {
			if(!repoIOrders.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ 'Message': 'User does not exist' }");
			}
			
			OrderDTO getAllOrdersResponseBody = repoIOrders.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(getAllOrdersResponseBody);
			
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{ 'Error': 'Server Error' },{'Message': "+e.getMessage()+"}");
		}
		
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> insertNewOrder(@Validated @RequestBody OrderDTO orderDto) {

		
		try {
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
			 
					
			 OrderDTO orderInsertedResponseBody = repoIOrders.insert(orderDto);
			return new ResponseEntity<OrderDTO>(orderInsertedResponseBody, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{ 'Error': 'Server Error' },{'Message': "+e.getMessage()+"}");
		}
	}

	@PutMapping
	public ResponseEntity<?> updateOrder(@Validated @RequestBody OrderDTO orderDto) {
		try {
			if(orderDto.getOrder_number().equals("") || orderDto.getOrder_number() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ 'Message': 'Bad Request' }");
			}
			OrderDTO orderUpdatedResponseBody = repoIOrders.save(orderDto);
			
			return new ResponseEntity<OrderDTO>(orderUpdatedResponseBody, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{ 'Error': 'Server Error' },{'Message': "+e.getMessage()+"}");
		}

	}
	
	@DeleteMapping
	public ResponseEntity<?>  deleteOrder(@PathVariable String id) {
		
		try {
			if(id == null || id.equals("")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ 'Message': 'Bad Request' }");
			}
			repoIOrders.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("{ 'Status': 'Deleted' }");
			
		} catch (Exception e) {
			return ResponseEntity.status(404).body("{ 'Error': 'Server Error' },{'Message': "+e.getMessage()+"}");
		}
	}

}
