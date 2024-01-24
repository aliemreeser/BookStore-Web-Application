package com.example.bookstore.repository;

import com.example.bookstore.entity.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
