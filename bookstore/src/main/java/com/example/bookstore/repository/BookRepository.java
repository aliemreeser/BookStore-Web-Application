package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Genre;
import com.example.bookstore.entity.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthor(Author author);
    List<Book> findByGenre(Genre author);
    List<Book> findByPublisher(Publisher publisher);


}
