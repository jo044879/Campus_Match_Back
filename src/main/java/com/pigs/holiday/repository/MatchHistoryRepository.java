package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {

    // 홈 팀이든 원정 팀이든, 내 클럽 ID가 포함된 경기를 모두 찾아오는 쿼리
    @Query("SELECT m FROM MatchHistory m WHERE m.homeClub.id = :clubId OR m.awayClub.id = :clubId ORDER BY m.matchDate DESC")
    List<MatchHistory> findByClubId(@Param("clubId") Long clubId);

}