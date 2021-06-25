package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.OrderDTO;

@Repository
public interface IOrderDAO extends MongoRepository<OrderDTO, String>{

}
