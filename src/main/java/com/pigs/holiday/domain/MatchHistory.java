package com.pigs.holiday.domain;

import com.pigs.holiday.dto.MatchHistoryDto;
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
    String location;
    String matchType;
    String result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    @OneToMany(mappedBy = "matchHistory")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "matchHistory")
    private List<GalleryImage> galleryImageList = new ArrayList<>();


    protected MatchHistory() {
    }

    private MatchHistory(LocalDateTime matchDate, String location, String matchType, String result, Club homeClub, Club awayClub) {
        this.matchDate = matchDate;
        this.location = location;
        this.matchType = matchType;
        this.result = result;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }

    public static MatchHistory of(LocalDateTime matchDate, String location, String matchType, String result, Club homeClub, Club awayClub) {
        return new MatchHistory(matchDate, location, matchType, result, homeClub, awayClub);
    }

    public MatchHistoryDto.CreateResDto toCreateResDto() {
        return MatchHistoryDto.CreateResDto.builder().id(getId()).build();
    }
}
