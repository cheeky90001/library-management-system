package com.library.management.system.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
