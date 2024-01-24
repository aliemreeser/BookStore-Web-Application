package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Publisher;
import com.example.bookstore.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable String id) {
        return ResponseEntity.ok(publisherService.getPublisherById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.createPublisher(publisher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable String id, @RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.updatePublisher(id, publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublisher(@PathVariable String id) throws Exception {
        publisherService.deletePublisher(id);
        return ResponseEntity.ok().build();
    }
}
