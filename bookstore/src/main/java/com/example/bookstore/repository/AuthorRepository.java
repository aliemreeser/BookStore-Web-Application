package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
