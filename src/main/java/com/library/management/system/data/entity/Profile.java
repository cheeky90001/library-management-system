package com.library.management.system.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(generator = "users_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @Column(name = "personal_id")
    private String personalId;
    @OneToMany(mappedBy = "profile")
    private List<Rental> rentals;
}
