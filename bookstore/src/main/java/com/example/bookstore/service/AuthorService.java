package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    public AuthorService(AuthorRepository authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(String id, Author updatedAuthor) {
        Optional<Author> existingAuthorOptional = authorRepository.findById(id);

        if (existingAuthorOptional.isPresent()) {
            Author existingAuthor = existingAuthorOptional.get();
            existingAuthor.setName(updatedAuthor.getName());
            return authorRepository.save(existingAuthor);
        } else {
            // Hata işlemleri
            return null;
        }
    }

    public void deleteAuthor(String id) throws Exception {
        Author author=new Author();
        author.setId(id);
        List<Book> books=bookService.getBookByAuthor(author);
        if(!books.isEmpty()){
            throw new Exception("This record cannot be deleted. There are books by this author.");
        }

        authorRepository.deleteById(id);
    }
}
