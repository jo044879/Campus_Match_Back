package com.pigs.holiday.domain;

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
public class MatchHistory extends AuditingFields {
    LocalDateTime matchDate;
    int homeScore;
    int awayScore;
    String result;
    Boolean isOfficial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mvp_player_id", nullable = false)
    private User mvpPlayer;

    @OneToMany(mappedBy = "matchHistory")
    private List<Review> reviewList = new ArrayList<>();


    protected MatchHistory(){}
    private MatchHistory(LocalDateTime matchDate, int homeScore, int awayScore, String result, Boolean isOfficial, Club homeClub, Club awayClub, User mvpPlayer) {
        this.matchDate = matchDate;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.result = result;
        this.isOfficial = isOfficial;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.mvpPlayer = mvpPlayer;
    }
    public static MatchHistory of(LocalDateTime matchDate, int homeScore, int awayScore, String result, Boolean isOfficial, Club homeClub, Club awayClub, User mvpPlayer) { return new MatchHistory(matchDate, homeScore, awayScore, result, isOfficial, homeClub, awayClub, mvpPlayer); }

    public MatchHistoryDto.CreateResDto toCreateResDto() { return MatchHistoryDto.CreateResDto.builder().id(getId()).build(); }
}
