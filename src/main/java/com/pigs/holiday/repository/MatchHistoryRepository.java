package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
}