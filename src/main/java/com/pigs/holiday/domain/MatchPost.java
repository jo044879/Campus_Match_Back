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
    String startTime;
    String endTime;
    LocalDate matchDate;
    String location;
    Boolean status;
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    protected MatchPost() {}
    private MatchPost(String sportCategory, String startTime, String endTime, LocalDate matchDate, String location,  Boolean status, String content,  Club homeClub, Club awayClub) {
        this.sportCategory = sportCategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.matchDate = matchDate;
        this.location = location;
        this.status = status;
        this.content = content;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }
    public static MatchPost of(String sportCategory, String startTime, String endTime, LocalDate matchDate, String location,  Boolean status, String content,  Club homeClub, Club awayClub) {
        return new MatchPost(sportCategory, startTime, endTime, matchDate, location, status, content, homeClub, awayClub);
    }
}
