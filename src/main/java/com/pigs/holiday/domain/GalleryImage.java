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
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;

    protected GalleryImage(){}
    private GalleryImage(String imageUrl, Gallery gallery) {
        this.imageUrl = imageUrl;
        this.gallery = gallery;
    }
    public static GalleryImage of(String imageUrl, Gallery gallery) {
        return new GalleryImage(imageUrl, gallery);
    }
}
