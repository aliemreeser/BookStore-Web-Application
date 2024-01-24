package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Genre;
import com.example.bookstore.entity.Publisher;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBookByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }
    public List<Book> getBookByPublisher(Publisher publisher) {
        return bookRepository.findByPublisher(publisher);
    }



    public Book updateBook(String id, Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setPrice(updatedBook.getPrice());
            return bookRepository.save(existingBook);
        } else {
            // Hata işlemleri
            return null;
        }
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
