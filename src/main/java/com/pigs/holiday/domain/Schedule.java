package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Schedule extends AuditingFields {
    String title;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Schedule(){}
    private Schedule(String title, LocalDate startDate, LocalDate endDate, Club club, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.club = club;

    }
    public static Schedule of(String title, LocalDate startDate, LocalDate endDate, Club club, LocalTime startTime, LocalTime endTime) {
        return new Schedule(title, startDate, endDate, club, startTime, endTime);
    }


}
