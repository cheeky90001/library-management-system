package com.library.management.system.repository;

import com.library.management.system.data.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Profile, Long> {
}
