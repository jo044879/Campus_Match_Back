package com.pigs.holiday.domain;

import com.pigs.holiday.dto.ClubDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ClubTest extends AuditingFields {
    String name;
    String university;
    String sportCategory;
    String region;
    String description;
    String logoUrl;
    int mannerScore;
    int totalWins;
    int totalMatches;

    @OneToMany(mappedBy = "clubTest")
    private List<ClubMember> clubMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "receiverClubTest")
    private List<MatchRequest> receiveMatchList = new ArrayList<>();

    @OneToMany(mappedBy = "senderClubTest")
    private List<MatchRequest> senderClubIdList = new ArrayList<>();

    @OneToMany(mappedBy = "clubTest")
    private List<MatchPost> matchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "homeClubTest")
    private List<MatchHistory> homeHistory = new ArrayList<>();

    @OneToMany(mappedBy = "awayClubTest")
    private List<MatchHistory> awayHistory = new ArrayList<>();

    @OneToMany(mappedBy = "clubTest")
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewerClubTest")
    private List<Review> toReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "targetClubTest")
    private List<Review> reviewList = new ArrayList<>();

    protected ClubTest(){}
    private ClubTest(String name, String university, String sportCategory, String region, String description, String logoUrl, int mannerScore, int totalWins, int totalMatches) {
        this.name = name;
        this.university = university;
        this.sportCategory = sportCategory;
        this.region = region;
        this.description = description;
        this.logoUrl = logoUrl;
        this.mannerScore = mannerScore;
        this.totalWins = totalWins;
        this.totalMatches = totalMatches;
    }
    public static ClubTest of(String name, String university, String sportCategory, String region, String description, String logoUrl, int mannerScore, int totalWins, int totalMatches) { return new ClubTest(name, university, sportCategory, region, description, logoUrl, mannerScore, totalWins, totalMatches); }

    public ClubDto.CreateResDto toCreateResDto() { return ClubDto.CreateResDto.builder().id(getId()).build(); }
}
