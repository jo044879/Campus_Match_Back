package com.pigs.holiday.repository;

import com.pigs.holiday.domain.ClubTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<ClubTest, Long> {
}