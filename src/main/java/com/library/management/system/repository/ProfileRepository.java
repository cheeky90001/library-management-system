package com.library.management.system.repository;

import com.library.management.system.data.entity.Profile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT p.*, b.* FROM profile p " +
                   "JOIN rental r ON p.id = r.profile_id " +
                   "JOIN book b on b.id = r.book_id " +
                   "WHERE p.id = :id",
            nativeQuery = true)
    List<Object[]> findProfileWithBooksByUsername(@Param("id") Long id);
}
