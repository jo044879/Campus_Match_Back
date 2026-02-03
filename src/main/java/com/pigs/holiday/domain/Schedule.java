package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Schedule extends AuditingFields {
    String title;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Schedule(){}
    private Schedule(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, String location, Club club) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.club = club;
    }
    public static Schedule of(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, String location, Club club) { return new Schedule(title, startDateTime, endDateTime, location, club); }

    public ScheduleDto.CreateResDto toCreateResDto() { return ScheduleDto.CreateResDto.builder().id(getId()).build(); }
}
