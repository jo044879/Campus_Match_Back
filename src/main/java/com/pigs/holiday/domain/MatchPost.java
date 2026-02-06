package com.pigs.holiday.domain;

import com.pigs.holiday.dto.MatchPostDto;
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
public class MatchPost extends AuditingFields {
    String sportCategory;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDateTime matchDate;
    String location;
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opposition_club_id", nullable = false)
    private Club oppositionClub;

    @OneToMany(mappedBy = "Notification")
    private List<Notification> notificationsList = new ArrayList<>();

    protected MatchPost() {
    }

    private MatchPost(String sportCategory, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime matchDate, String location, String status, Club club, Club oppositionClub) {
        this.sportCategory = sportCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.matchDate = matchDate;
        this.location = location;
        this.status = status;
        this.club = club;
        this.oppositionClub = oppositionClub;
    }

    public static MatchPost of(String sportCategory, LocalDateTime startTime, LocalDateTime endTime,LocalDateTime matchDate, String location, String status, Club club, Club oppositionClub)  {
        return new MatchPost(sportCategory, startTime, endTime, matchDate, location, status, club, oppositionClub);
    }

    public MatchPostDto.CreateResDto toCreateResDto() {
        return MatchPostDto.CreateResDto.builder().id(getId()).build();
    }
}
