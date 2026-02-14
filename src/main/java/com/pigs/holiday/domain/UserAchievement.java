package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserAchievement extends AuditingFields {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id", nullable = false) // 컬럼명 명시 권장
    private Achievement achievement;


    private UserAchievement(Club club, Achievement achievement) {
        this.club = club;
        this.achievement = achievement;
    }

    public static UserAchievement of(Club club, Achievement achievement) {
        return new UserAchievement(club, achievement);
    }
}