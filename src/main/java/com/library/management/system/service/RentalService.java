package com.library.management.system.service;

import com.library.management.system.data.dto.ProfilesRentedBooksResponseDTO;
import com.library.management.system.data.entity.Book;
import com.library.management.system.data.entity.Profile;
import com.library.management.system.data.entity.Rental;
import com.library.management.system.data.dto.RentalResponseDTO;
import com.library.management.system.repository.RentalRepository;
import com.library.management.system.repository.ProfileRepository;
import com.library.management.system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final ProfileRepository profileRepository;
    private final BookRepository bookRepository;

    public RentalResponseDTO rentBook(Long userId, Long bookId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setIsAvailable(false);

        Rental rental = Rental.builder()
                .book(book)
                .profile(profile)
                .rentedAt(LocalDateTime.now())
                .returnedAt(null).build();

        Rental saved = rentalRepository.save(rental);
        return toDto(saved);
    }

    private RentalResponseDTO toDto(Rental saved) {
        return RentalResponseDTO.builder()
                .id(saved.getId())
                .profileId(saved.getProfile().getId())
                .bookId(saved.getBook().getId())
                .rentedAt(saved.getRentedAt())
                .returnedAt(saved.getReturnedAt())
                .build();
    }

    public ProfilesRentedBooksResponseDTO getAllRentedBooks(Long profileId) {
        List<Rental> rentedData = rentalRepository.findAllByProfileId(profileId);

        rentedData.stream().filter(rental -> rental.getBook() != null).forEach(rental -> );

    }
}
