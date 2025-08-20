package com.library.management.system.repository;

import com.library.management.system.data.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    List<Book> findByAuthor(String author);
}
