package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Genre;
import com.example.bookstore.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {


    private final GenreRepository genreRepository;
    private final BookService bookService;

    public GenreService(GenreRepository genreRepository, BookService bookService) {
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(String id) {
        return genreRepository.findById(id);
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(String id, Genre updatedGenre) {
        Optional<Genre> existingGenreOptional = genreRepository.findById(id);

        if (existingGenreOptional.isPresent()) {
            Genre existingGenre = existingGenreOptional.get();
            existingGenre.setName(updatedGenre.getName());


            return genreRepository.save(existingGenre);
        } else {
            // Hata işlemleri
            return null;
        }
    }

    public void deleteGenre(String id) throws Exception {
        Genre genre = new Genre();
        genre.setId(id);
        List<Book> books = bookService.getBookByGenre(genre);
        if (!books.isEmpty()) {
            throw new Exception("This record cannot be deleted. There are books by this genre.");
        }

        genreRepository.deleteById(id);
    }
}