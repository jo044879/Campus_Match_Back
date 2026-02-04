package com.pigs.holiday.domain;

import com.pigs.holiday.dto.MatchRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchRequest extends AuditingFields {
    String message;
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_club_id", nullable = false)
    private Club receiveClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_club_id", nullable = false)
    private Club senderClub;

    protected MatchRequest(){}
    private MatchRequest(String message, String status, Club receiveClub, Club senderClub) {
        this.message = message;
        this.status = status;
        this.receiveClub = receiveClub;
        this.senderClub = senderClub;
    }
    public static MatchRequest of(String message, String status, Club receiveClub, Club senderClub) { return new MatchRequest(message, status, receiveClub, senderClub); }

    public MatchRequestDto.CreateResDto toCreateResDto() { return MatchRequestDto.CreateResDto.builder().id(getId()).build(); }
}
