package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchPostRepository extends JpaRepository<MatchPost, Long> {
    List<MatchPost> findByDeletedAndStatus(Boolean deleted, Boolean status);
    List<MatchPost> findByHomeClubAndDeletedAndStatusAndMatchDateGreaterThan(Club homeClub, Boolean Deleted, Boolean status, LocalDate targetDate);
    List<MatchPost> findByAwayClubAndDeletedAndStatusAndMatchDateGreaterThan(Club awayClub, Boolean Deleted, Boolean status, LocalDate targetDate);
    List<MatchPost> findByHomeClubAndDeletedAndStatusAndMatchDate(Club homeClub, Boolean Deleted, Boolean status, LocalDate matchDate);
    List<MatchPost> findByAwayClubAndDeletedAndStatusAndMatchDate(Club awayClub, Boolean Deleted, Boolean status, LocalDate matchDate);
    List<MatchPost> findByHomeClubAndStatusAndDeleted(Club homeClub, Boolean status, Boolean deleted);
    List<MatchPost> findByAwayClubAndStatusAndDeleted(Club awayClub, Boolean status, Boolean deleted);
    List<MatchPost> findByStatusAndDeletedAndMatchDateLessThan(Boolean status, Boolean deleted, LocalDate targetDate);

    List<MatchPost> findByHomeClubAndDeletedAndStatus(Club homeClub, Boolean deleted, Boolean status);

    List<MatchPost> findByAwayClubAndDeleted(Club awayClub, Boolean deleted);
}
