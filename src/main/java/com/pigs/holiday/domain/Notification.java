package com.pigs.holiday.domain;

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
    String type;
    String content;
    String relatedUrl;
    Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Notification(){}
    private Notification(String type, String content, String relatedUrl, Boolean isRead, User user) {
        this.type = type;
        this.content = content;
        this.relatedUrl = relatedUrl;
        this.isRead = isRead;
        this.user = user;
    }
    public static Notification of(String type, String content, String relatedUrl, Boolean isRead, User user) { return new Notification(type, content, relatedUrl, isRead, user); }

    public NotificationDto.CreateResDto toCreateResDto() { return NotificationDto.CreateResDto.builder().id(getId()).build(); }
}
