package com.pigs.holiday.domain;

import com.pigs.holiday.dto.MatchRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchRequest extends AuditingFields {

    String location;
    String sportCategory;
    LocalDateTime date;
    LocalDateTime startTime;
    LocalDateTime endTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_club_id", nullable = false)
    private Club receiveClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_club_id", nullable = false)
    private Club senderClub;

    @OneToMany(mappedBy = "Notification")
    private List<Notification> notificationsList = new ArrayList<>();

    protected MatchRequest() {
    }

    private MatchRequest(String location, String sportCategory, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Club receiveClub, Club senderClub) {
        this.location = location;
        this.receiveClub = receiveClub;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.senderClub = senderClub;
        //receiveClub은 없어도 되는지 ?
    }

    public static MatchRequest of(String location, String sportCategory, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Club receiveClub, Club senderClub) {
        return new MatchRequest(location, sportCategory, date, startTime, endTime, receiveClub, senderClub);
    }

    public MatchRequestDto.CreateResDto toCreateResDto() {
        return MatchRequestDto.CreateResDto.builder().id(getId()).build();
    }
}
