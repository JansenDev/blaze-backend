package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProductDTO;

@Repository
public interface IProductDAO extends MongoRepository<ProductDTO, String>{

}
