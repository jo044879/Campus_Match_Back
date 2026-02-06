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
public class Club extends AuditingFields {
    String username;
    String password;
    String name;
    String university;
    String phone;
    String email;
    String clubName;
    String description;
    String region;
    String sportCategory;
    String logoUrl;//이건 일단 남겨둠
    int mannerScore;
    int totalWins;
    int totalLosses;
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

    protected Club() {
    }

    private Club(String username, String password, String name,String university, String email, String phone, String clubName, String description, String sportCategory, String region,  String logoUrl, int mannerScore, int totalWins, int totalMatches, int totalLosses) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clubName = clubName;
        this.university = university;
        this.sportCategory = sportCategory;
        this.region = region;
        this.description = description;
        this.logoUrl = logoUrl;
        this.mannerScore = mannerScore;
        this.totalWins = totalWins;
        this.totalMatches = totalMatches;
        this.totalLosses = totalLosses;
    }

    public static Club of(String username, String password, String name,String university, String email, String phone, String clubName, String description, String sportCategory, String region,  String logoUrl, int mannerScore, int totalWins, int totalMatches, int totalLosses) {
        return new Club(username, password, name, university, email, phone, clubName, sportCategory, region, description, logoUrl, mannerScore, totalWins, totalMatches, totalLosses);
    }

    public ClubDto.CreateResDto toCreateResDto() {
        return ClubDto.CreateResDto.builder().id(getId()).build();
    }
}
