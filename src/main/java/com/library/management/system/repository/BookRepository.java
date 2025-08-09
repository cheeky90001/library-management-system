package com.library.management.system.repository;

import com.library.management.system.data.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    List<Book> findByAuthor(String author);
}
