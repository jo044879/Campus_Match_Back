package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchPost extends AuditingFields {
    String sportCategory;
    LocalDate matchDate;
    String location;
    String startTime;
    String endTime;
    String content;
    Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    protected MatchPost() {}
    private MatchPost(String sportCategory, LocalDate matchDate, String location, String startTime, String endTime, String content, Boolean status, Club homeClub, Club awayClub) {
        this.sportCategory = sportCategory;
        this.matchDate = matchDate;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.status = status;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }
    public static MatchPost of(String sportCategory, LocalDate matchDate, String location, String startTime, String endTime, String content, Boolean status, Club homeClub, Club awayClub) {
        return new MatchPost(sportCategory, matchDate, location, startTime, endTime, content, status, homeClub, awayClub);
    }
}
