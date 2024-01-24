package com.example.bookstore.repository;

import com.example.bookstore.entity.OrderDetail;
import com.example.bookstore.entity.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublisherRepository extends MongoRepository<Publisher, String> {
}

