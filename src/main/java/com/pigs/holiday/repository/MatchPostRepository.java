package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchPostRepository extends JpaRepository<MatchPost, Long> {
    List<MatchPost> findByDeletedAndStatus(Boolean deleted, Boolean status);
    List<MatchPost> findByHomeClubAndStatusAndMatchDateGreaterThan(Club homeClub, Boolean status, LocalDate targetDate);
    List<MatchPost> findByAwayClubAndStatusAndMatchDateGreaterThan(Club awayClub, Boolean status, LocalDate targetDate);
    List<MatchPost> findByHomeClubAndStatusAndMatchDate(Club homeClub, Boolean status, LocalDate matchDate);
    List<MatchPost> findByAwayClubAndStatusAndMatchDate(Club awayClub, Boolean status, LocalDate matchDate);

}
