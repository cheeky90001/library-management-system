package com.library.management.system.repository;

import com.library.management.system.data.entity.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAllByProfileId(Long profileId);
}
