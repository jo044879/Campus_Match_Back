package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Gallery;
import lombok.*;

import java.time.LocalDate;

public class GalleryDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String title;
        LocalDate matchDate;

        public Gallery toEntity() { return Gallery.of(getMatchDate(), getTitle(), null); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long galleryId;

        public static CreateResDto toCreateResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }
}
