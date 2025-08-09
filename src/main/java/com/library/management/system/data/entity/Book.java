package com.library.management.system.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(generator = "book_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_id_gen", sequenceName = "book_id_seq")
    private Long id;
    private String name;
    private String author;
    private Boolean isAvailable;
}
