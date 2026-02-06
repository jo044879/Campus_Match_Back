package com.pigs.holiday.domain;

import com.pigs.holiday.dto.NotificationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Notification extends AuditingFields {
    String scheduledAt;
    String relatedUrl;
    Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Match_Post_id", nullable = false)
    private MatchPost matchPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MatchRequest_id", nullable = false)
    private MatchRequest matchRequest;

    protected Notification() {
    }

    private Notification(String scheduledAt, String relatedUrl, Boolean isRead, User user) {
        this.scheduledAt = scheduledAt;
        this.relatedUrl = relatedUrl;
        this.isRead = isRead;
        this.user = user;
    }

    public static Notification of(String scheduledAt, String relatedUrl, Boolean isRead, User user) {
        return new Notification(scheduledAt, relatedUrl, isRead, user);
    }

    public NotificationDto.CreateResDto toCreateResDto() {
        return NotificationDto.CreateResDto.builder().id(getId()).build();
    }
}
