package com.library.management.system.service;

import com.library.management.system.data.dto.BookDTO;
import com.library.management.system.data.entity.Book;
import com.library.management.system.data.entity.Profile;
import com.library.management.system.data.entity.Rental;
import com.library.management.system.data.dto.RentalResponseDTO;
import com.library.management.system.repository.RentalRepository;
import com.library.management.system.repository.ProfileRepository;
import com.library.management.system.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<BookDTO> getAllRentedBooks(Long profileId) {
        log.info("Fetching rentals for profileId: {}", profileId);
        assert profileId != null : "Profile id is null";

        List<Rental> rentedData = rentalRepository.findAllByProfileId(profileId);
        if (rentedData.isEmpty()) {
            log.warn("No rentals found for profileId: {}", profileId);
            return new ArrayList<>();
        }
        return rentedData.stream()
                .filter(rental -> rental.getBook() != null)
                .map(rental -> createBookDTO(rental.getBook()))
                .toList();
    }

    private BookDTO createBookDTO(Book book) {
        return BookDTO.builder().name(book.getName()).author(book.getAuthor()).build();
    }

    public ResponseEntity returnBook(Long profileId, Long bookId) {
        if (profileId == null || bookId == null) {
            throw new IllegalArgumentException("Missing key parameters to search book for");
        }        List<Rental> rentedBooks = rentalRepository.findAllByProfileId(profileId);
        if (Objects.isNull(rentedBooks) || rentedBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Rental rentedBook = rentedBooks.stream().filter(rental -> Objects.equals(rental.getBook().getId(), bookId)).findFirst().orElse(null);
        if (rentedBook == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Book book = rentedBook.getBook();
            book.setIsAvailable(Boolean.TRUE);
            rentalRepository.delete(rentedBook);
            bookRepository.save(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
