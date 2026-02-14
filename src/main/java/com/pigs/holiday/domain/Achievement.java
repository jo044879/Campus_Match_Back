package com.pigs.holiday.domain;

import com.pigs.holiday.domain.AchievementType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Achievement extends AuditingFields {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AchievementType type;

    @Column(nullable = false)
    private int goalCount;

    @OneToMany(mappedBy = "achievement")
    private List<UserAchievement> userAchievementsList = new ArrayList<>();

    private Achievement(String title, String content, String imageUrl, AchievementType type, int goalCount) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.type = type;
        this.goalCount = goalCount;
    }

    public static Achievement of(String title, String content, String imageUrl, AchievementType type, int goalCount) {
        return new Achievement(title, content, imageUrl, type, goalCount);
    }
}