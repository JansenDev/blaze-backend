package com.example.demo.controllers;

import java.util.List;

import org.apache.coyote.http11.Http11AprProtocol;
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
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.ProductDTO;
import com.example.demo.repositories.IProductDAO;

@RestController
@CrossOrigin(origins = "*", methods = { 
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE
		})
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductDAO repoIProducts;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAllProducts(){
		
		List<ProductDTO> getAllProductsResponseBody =  repoIProducts.findAll();
		
		
		return ResponseEntity.status(HttpStatus.OK).body(getAllProductsResponseBody);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insertProduct(@Validated @RequestBody ProductDTO productDTO){
		
		ProductDTO productAddedResponseBody = repoIProducts.insert(productDTO);
		
		return new ResponseEntity<ProductDTO>(productAddedResponseBody, HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<ProductDTO> updateProduct(@Validated @RequestBody ProductDTO productDTO){
		
		ProductDTO productUpdateddResponseBody = repoIProducts.save(productDTO);
		
		return new ResponseEntity<ProductDTO>(productUpdateddResponseBody, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable String id) {
		repoIProducts.deleteById(id);
	}
	
}
