package com.library.management.system.service;

import com.library.management.system.data.entity.Book;
import com.library.management.system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        if (book == null) {
            log.warn("Attempted to create a null book.");
            throw new IllegalArgumentException("Book cannot be null");
        }
        return bookRepository.save(book);
    }

    public ResponseEntity<?> getBook(String bookName) {
        if (bookName == null || bookName.trim().isEmpty()) {
            log.warn("Book name is null or empty.");
            new ResponseEntity<>("Book not found", HttpStatus.BAD_REQUEST);
        }
        log.info("Book name: {}", bookName);
        Book book = bookRepository.findByName(bookName);
        if (book == null) {
            log.info("No book found with name: {}", bookName);
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> getAllBooksByAuthor(String author) {
        log.info(author);
        List<Book> books = bookRepository.findByAuthor(author);
        if (books.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
