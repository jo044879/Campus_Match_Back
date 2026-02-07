package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Schedule extends AuditingFields {
    String title;
    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Schedule(){}
    private Schedule(String title, LocalDate startDate, LocalDate endDate, Club club) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.club = club;
    }
    public static Schedule of(String title, LocalDate startDate, LocalDate endDate, Club club) {
        return new Schedule(title, startDate, endDate, club);
    }
}
