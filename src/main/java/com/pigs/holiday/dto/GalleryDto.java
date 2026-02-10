package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class GalleryDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String title;
        LocalDate matchDate;
        Boolean isOfficial;

        public Gallery toEntity() { return Gallery.of(getMatchDate(), getTitle(), getIsOfficial(),null); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long galleryId;

        public static CreateResDto toCreateResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }

    // List
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long galleryId;
        String title;
        LocalDate matchDate;
        String imageUrl;
        Boolean isOfficial;

        public static ListResDto toListResDto(Gallery gallery) {
            return builder()
                    .galleryId(gallery.getId())
                    .title(gallery.getTitle())
                    .matchDate(gallery.getMatchDate())
                    .imageUrl(gallery.getGalleryImageList().get(0).getImageUrl())
                    .isOfficial(gallery.getIsOfficial())
                    .build();
        }
    }

    // Detail
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String title;
        LocalDate matchDate;
        Boolean isOfficial;
        Boolean myGallery;
        List<String> imageUrls;

        public static DetailResDto toDetailResDto(Gallery gallery, Boolean myGallery) {
            return builder()
                    .title(gallery.getTitle())
                    .matchDate(gallery.getMatchDate())
                    .isOfficial(gallery.getIsOfficial())
                    .myGallery(myGallery)
                    .imageUrls(gallery.getGalleryImageList().stream().map(GalleryImage::getImageUrl).toList())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        String title;
        LocalDate matchDate;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long galleryId;

        public static UpdateResDto toUpdateResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long galleryId;
        public static DeleteResDto toDeleteResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }
}
