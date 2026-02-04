package com.pigs.holiday.domain;

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
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploader_id", nullable = false)
    private User uploader;

    protected GalleryImage(){}
    private GalleryImage(String imageUrl, Club club, User uploader) {
        this.imageUrl = imageUrl;
        this.club = club;
        this.uploader = uploader;
    }
    public static GalleryImage of(String imageUrl, Club club, User uploader) { return new GalleryImage(imageUrl, club, uploader); }

    public GalleryImageDto.CreateResDto toCreateResDto() { return GalleryImageDto.CreateResDto.builder().id(getId()).build(); }
}
