package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    @Query("SELECT m FROM MatchHistory m WHERE (m.homeClub.id = :clubId OR m.awayClub.id = :clubId) AND m.deleted = false ORDER BY m.matchDate DESC")
    List<MatchHistory> findByClubId(@Param("clubId") Long clubId);
}