package com.example.bookstore.repository;

import com.example.bookstore.entity.Genre;
import com.example.bookstore.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}

