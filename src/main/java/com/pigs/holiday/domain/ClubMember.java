package com.pigs.holiday.domain;

import com.pigs.holiday.dto.ClubMemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ClubMember extends AuditingFields {
    String role;
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private ClubTest clubTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Club club;

    protected ClubMember(){}
    private ClubMember(String role, String status, ClubTest clubTest, Club club) {
        this.role = role;
        this.status = status;
        this.clubTest = clubTest;
        this.club = club;
    }
    public static ClubMember of(String role, String status, ClubTest clubTest, Club club) { return new ClubMember(role, status, clubTest, club); }

    public ClubMemberDto.CreateResDto toCreateResDto() { return ClubMemberDto.CreateResDto.builder().id(getId()).build(); }
}
