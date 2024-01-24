package com.example.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
    @Id
    private String id;
    private String title;

    private Double price;

    @DBRef
    private Author author;
    @DBRef
    private Genre genre;
    @DBRef
    private Publisher publisher;

    public Book() {
    }

    public Book(String id, String title, Double price, Author author, Genre genre, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
