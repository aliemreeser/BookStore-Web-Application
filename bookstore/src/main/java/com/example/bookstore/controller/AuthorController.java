package com.example.bookstore.controller;

import com.example.bookstore.entity.Author;
import com.example.bookstore.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable String id) {
        return ResponseEntity.ok(authorService.getAuthorById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable String id, @RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable String id) throws Exception {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }
}
