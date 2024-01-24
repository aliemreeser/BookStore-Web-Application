package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Genre;
import com.example.bookstore.entity.Publisher;
import com.example.bookstore.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {


    private final PublisherRepository publisherRepository;
    private final BookService bookService;

    public PublisherService(PublisherRepository publisherRepository, BookService bookService) {
        this.publisherRepository = publisherRepository;
        this.bookService = bookService;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(String id) {
        return publisherRepository.findById(id);
    }

    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher updatePublisher(String id, Publisher updatedPublisher) {
        Optional<Publisher> existingPublisherOptional = publisherRepository.findById(id);

        if (existingPublisherOptional.isPresent()) {
            Publisher existingPublisher = existingPublisherOptional.get();
            existingPublisher.setName(updatedPublisher.getName());

            return publisherRepository.save(existingPublisher);
        } else {
            // Hata işlemleri
            return null;
        }
    }

    public void deletePublisher(String id) throws Exception {

        Publisher publisher=new Publisher();
        publisher.setId(id);
        List<Book> books=bookService.getBookByPublisher(publisher);
        if(!books.isEmpty()){
            throw new Exception("This record cannot be deleted. There are books by this Publisher.");
        }

        publisherRepository.deleteById(id);
    }
}