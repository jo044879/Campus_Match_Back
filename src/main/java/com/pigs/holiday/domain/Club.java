package com.pigs.holiday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import com.pigs.holiday.dto.ClubDto;
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
    int totalMatches;
    int totalWins;
    int totalDraws;
    int totalLosses;
    double mannerScore;

    @OneToMany(mappedBy = "homeClub")
    private List<MatchHistory> homeMatchHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<MatchHistory> awayMatchHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Gallery> galleryList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "homeClub")
    private List<MatchPost> homeMatchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<MatchPost> awayMatchPostList = new ArrayList<>();

    protected Club(){}
    private Club(String username, String password, String name, String university, String phone, String email, String clubName, String description, String region, String sportCategory, int totalMatches, int totalWins, int totalDraws, int totalLosses, double mannerScore) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.university = university;
        this.phone = phone;
        this.email = email;
        this.clubName = clubName;
        this.description = description;
        this.region = region;
        this.sportCategory = sportCategory;
        this.totalMatches = totalMatches;
        this.totalWins = totalWins;
        this.totalDraws = totalDraws;
        this.totalLosses = totalLosses;
        this.mannerScore = mannerScore;
    }
    public static Club of(String username, String password, String name, String university, String phone, String email, String clubName, String description, String region, String sportCategory, int totalMatches, int totalWins, int totalDraws, int totalLosses, double mannerScore) {
        return new Club(username, password,  name, university, phone, email, clubName, description, region, sportCategory, totalMatches, totalWins, totalDraws, totalLosses, mannerScore);
    }





    public ClubDto.SignupResDto toSignupResDto() { return ClubDto.SignupResDto.builder().id(getId()).build(); }
}
