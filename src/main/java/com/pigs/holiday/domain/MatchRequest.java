package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchRequest extends AuditingFields {
    LocalTime startTime;
    LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_post_id", nullable = false)
    private MatchPost matchPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_club_id", nullable = false)
    private Club senderClub;

    protected MatchRequest() {}
    private MatchRequest(LocalTime StartTime, LocalTime EndTime, MatchPost matchPost, Club senderClub) {
        this.startTime = StartTime;
        this.endTime = EndTime;
        this.matchPost = matchPost;
        this.senderClub = senderClub;
    }
    public static MatchRequest of(LocalTime StartTime, LocalTime EndTime, MatchPost matchPost, Club senderClub) {
        return new MatchRequest(StartTime, EndTime, matchPost, senderClub);
    }
}
