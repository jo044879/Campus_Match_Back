package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByUsername(String username);
}
