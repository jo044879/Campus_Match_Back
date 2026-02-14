package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Boolean existsByClubAndIsReadAndNotiDateLessThanEqual(Club club, Boolean isRead, LocalDate targetDate);
    int countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(Club club, Boolean isRead, String notiType, LocalDate targetDate);
    List<Notification> findByClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(Club club, String notiType, LocalDate notiDateIsLessThan);
}
