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
public class Gallery extends AuditingFields {
    LocalDate matchDate;
    String title;
    Boolean isOfficial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @OneToMany(mappedBy = "gallery")
    private List<GalleryImage> galleryImageList = new ArrayList<>();

    protected Gallery(){}
    private Gallery(LocalDate matchDate, String title,Boolean isOfficial, Club club) {
        this.matchDate = matchDate;
        this.title = title;
        this.isOfficial = isOfficial;
        this.club = club;
    }
    public static Gallery of(LocalDate matchDate, String title,Boolean isOfficial, Club club) {
        return new Gallery(matchDate, title, isOfficial, club);
    }
}
