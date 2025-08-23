package com.library.management.system.controller;

import com.library.management.system.data.dto.BookDTO;
import com.library.management.system.data.entity.Profile;
import com.library.management.system.data.dto.RentalResponseDTO;
import com.library.management.system.service.ProfileService;
import com.library.management.system.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileService profileService;
    private final RentalService rentalService;

    @GetMapping("")
    public ResponseEntity<?> getProfile(@RequestParam Long profileId) {
        return profileService.getProfile(profileId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @PostMapping("")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.saveProfile(profile), HttpStatus.CREATED);
    }

    @PostMapping("/{profileId}/rent/{bookId}")
    public ResponseEntity<RentalResponseDTO> rentBook(@PathVariable Long profileId, @PathVariable Long bookId) {
        RentalResponseDTO response = rentalService.rentBook(profileId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{profileId}/all-books")
    public ResponseEntity<List<BookDTO>> getAllProfilesRentedBooks(@PathVariable Long profileId) {
        log.info("Fetching all rented books for profileId: {}", profileId);
        List<BookDTO> books =  rentalService.getAllRentedBooks(profileId);
        if (books.isEmpty()) {
            log.warn("No rented books found for profileId: {}", profileId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/{profileId}/return-book/{bookId}")
    public ResponseEntity returnBook(@PathVariable Long profileId, @PathVariable Long bookId) {
        log.info("Hi all im in this api now");
        return rentalService.returnBook(profileId, bookId);
    }

    //Create users, get users, get specific user, rent Book
    //Books : Get specific book, get all books, get book by author?
}
