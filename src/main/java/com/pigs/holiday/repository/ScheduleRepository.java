package com.pigs.holiday.repository;


import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClubIdAndDeleted(Long clubId, Boolean deleted);
}
