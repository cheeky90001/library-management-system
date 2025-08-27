package com.library.management.system.controller;

import com.library.management.system.data.entity.Book;
import com.library.management.system.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getBook(@RequestParam String searchParam) {
        return bookService.getBooks(searchParam);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/authors/{author}")
    public ResponseEntity<List<Book>> getAllBooksByAuthor(@PathVariable String author) {
        return bookService.getAllBooksByAuthor(author);
    }
}
