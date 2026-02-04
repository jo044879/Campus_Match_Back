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
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected ClubMember(){}
    private ClubMember(String role, String status, Club club, User user) {
        this.role = role;
        this.status = status;
        this.club = club;
        this.user = user;
    }
    public static ClubMember of(String role, String status, Club club, User user) { return new ClubMember(role, status, club, user); }

    public ClubMemberDto.CreateResDto toCreateResDto() { return ClubMemberDto.CreateResDto.builder().id(getId()).build(); }
}
