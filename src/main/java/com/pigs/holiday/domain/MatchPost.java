package com.pigs.holiday.domain;

import com.pigs.holiday.dto.MatchPostDto;
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
public class MatchPost extends AuditingFields {
    String sportCategory;
    LocalDateTime matchDate;
    String location;
    String description;
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @OneToMany(mappedBy = "Notification")
    private List<Notification> notificationsList = new ArrayList<>();

    protected MatchPost(){}
    private MatchPost(String sportCategory, LocalDateTime matchDate, String location, String description, String status, Club club) {
        this.sportCategory = sportCategory;
        this.matchDate = matchDate;
        this.location = location;
        this.description = description;
        this.status = status;
        this.club = club;
    }
    public static MatchPost of(String sportCategory, LocalDateTime matchDate, String location, String description, String status, Club club) { return new MatchPost(sportCategory, matchDate, location, description, status, club); }

    public MatchPostDto.CreateResDto toCreateResDto() { return MatchPostDto.CreateResDto.builder().id(getId()).build(); }
}
