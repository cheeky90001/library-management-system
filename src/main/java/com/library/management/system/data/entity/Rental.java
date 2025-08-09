package com.library.management.system.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "rental")
public class Rental {

    @GeneratedValue(generator = "rental_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rental_id_gen", sequenceName = "rental_id_seq")
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;
}
