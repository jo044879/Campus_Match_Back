package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Achievement;
import com.pigs.holiday.domain.AchievementType;
import com.pigs.holiday.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByTypeAndGoalCount(AchievementType type, int goalCount);
}
