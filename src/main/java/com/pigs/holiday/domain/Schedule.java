package com.pigs.holiday.domain;

import com.pigs.holiday.dto.ScheduleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Schedule extends AuditingFields {
    String title;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @OneToMany(mappedBy = "Notification")
    private List<Notification> notificationsList = new ArrayList<>();

    protected Schedule() {
    }

    private Schedule(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, Club club) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.club = club;
    }

    public static Schedule of(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, Club club) {
        return new Schedule(title, startDateTime, endDateTime, club);
    }

    public ScheduleDto.CreateResDto toCreateResDto() {
        return ScheduleDto.CreateResDto.builder().id(getId()).build();
    }
}
