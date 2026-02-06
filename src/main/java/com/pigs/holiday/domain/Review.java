package com.pigs.holiday.domain;

import com.pigs.holiday.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



//리뷰는 erd에 존재하지 않는데 일단 삭제하지 않고 놔둠
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Review extends AuditingFields {
    int rating;
    String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_history_id", nullable = false)
    private MatchHistory matchHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_club_id", nullable = false)
    private Club reviewerClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_club_id", nullable = false)
    private Club targetClub;

    protected Review(){}
    private Review(int rating, String comment, MatchHistory matchHistory, Club reviewerClub, Club targetClub) {
        this.rating = rating;
        this.comment = comment;
        this.matchHistory = matchHistory;
        this.reviewerClub = reviewerClub;
        this.targetClub = targetClub;
    }
    public static Review of(int rating, String comment,MatchHistory matchHistory, Club reviewerClub, Club targetClub) { return new Review(rating, comment, matchHistory, reviewerClub, targetClub); }

    public ReviewDto.CreateResDto toCreateResDto() { return ReviewDto.CreateResDto.builder().id(getId()).build(); }
}
