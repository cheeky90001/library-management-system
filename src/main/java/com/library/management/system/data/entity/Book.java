package com.library.management.system.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @OneToMany(mappedBy = "book")
    private List<Rental> rentals;
    private Boolean isAvailable;
}
