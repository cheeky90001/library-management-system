package com.library.management.system.service;

import com.library.management.system.data.entity.Profile;
import com.library.management.system.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ResponseEntity<?> getProfile(Long profileId) {
        log.info("user id: {}", profileId);
        Profile profile = profileRepository.findById(profileId).orElse(null);
        if (profile == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) {
            return new ResponseEntity<>("There is no users present in db", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    public Profile saveProfile(Profile profile) {
        log.info("Saving new user: {}", profile);
        return profileRepository.save(profile);
    }
}
