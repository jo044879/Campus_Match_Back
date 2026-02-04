package com.pigs.holiday.domain;

import com.pigs.holiday.dto.GalleryImageDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class GalleryImage extends AuditingFields {
    String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_history_id", nullable = false)
    private MatchHistory matchHistory;

    protected GalleryImage(){}
    private GalleryImage(String imageUrl, MatchHistory matchHistory) {
        this.imageUrl = imageUrl;
        this.matchHistory  = matchHistory;
    }
    public static GalleryImage of(String imageUrl, MatchHistory matchHistory) { return new GalleryImage(imageUrl, matchHistory); }

    public GalleryImageDto.CreateResDto toCreateResDto() { return GalleryImageDto.CreateResDto.builder().id(getId()).build(); }
}
