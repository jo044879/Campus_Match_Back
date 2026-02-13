package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Notification extends AuditingFields {
    String notiType;
    LocalDate notiDate;
    String content;
    Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Notification(){}
    private Notification(String notiType, LocalDate notiDate, String content, Boolean isRead, Club club) {
        this.notiType = notiType;
        this.notiDate = notiDate;
        this.content = content;
        this.isRead = isRead;
        this.club = club;
    }
    public static Notification of(String notiType, LocalDate notiDate, String content, Boolean isRead, Club club) {
        return new Notification(notiType, notiDate, content, isRead, club);
    }
}
