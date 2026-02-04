package com.pigs.holiday.domain;

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
public class Club extends AuditingFields {
    String name;
    String university;
    String sportCategory;
    String region;
    String description;
    String logoUrl;
    int mannerScore;
    int totalWins;
    int totalMatches;

    @OneToMany(mappedBy = "club")
    private List<ClubMember> clubMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "receiverClub")
    private List<MatchRequest> receiveMatchList = new ArrayList<>();

    @OneToMany(mappedBy = "senderClub")
    private List<MatchRequest> senderClubIdList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<MatchPost> matchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "homeClub")
    private List<MatchHistory> homeHistory = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<MatchHistory> awayHistory = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewerClub")
    private List<Review> toReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "targetClub")
    private List<Review> reviewList = new ArrayList<>();

    protected Club(){}
    private Club(String name, String university, String sportCategory, String region, String description, String logoUrl, int mannerScore, int totalWins, int totalMatches) {
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
    public static Club of(String name, String university, String sportCategory, String region, String description, String logoUrl, int mannerScore, int totalWins, int totalMatches) { return new Club(name, university, sportCategory, region, description, logoUrl, mannerScore, totalWins, totalMatches); }

    public ClubDto.CreateResDto toCreateResDto() { return ClubDto.CreateResDto.builder().id(getId()).build(); }
}
