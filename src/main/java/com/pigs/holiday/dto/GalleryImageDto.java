package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.domain.MatchHistory;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class GalleryImageDto {

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String imageUrl;
        MatchHistory matchHistory;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public GalleryImage toEntity() { return GalleryImage.of(
                getImageUrl(),
                getMatchHistory()
        ); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    // Detail Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Detail Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailSevDto extends DetailReqDto {
        Long reqUserId;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String imageUrl;
        MatchHistory matchHistory;

        public static GalleryImageDto.DetailResDto toDetailResDto(GalleryImage galleryImage) {
            return GalleryImageDto.DetailResDto.builder()
                    .id(galleryImage.getId())
                    .deleted(galleryImage.getDeleted())
                    .createdAt(galleryImage.getCreatedAt())
                    .modifiedAt(galleryImage.getModifiedAt())
                    .imageUrl(galleryImage.getImageUrl())
                    .matchHistory(galleryImage.getMatchHistory())
                    .build();
        }
    }

    // List Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListSevDto {
        Long reqUserId;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String imageUrl;
        MatchHistory matchHistory;

        public static GalleryImageDto.ListResDto toListResDto(GalleryImage galleryImage) {
            return GalleryImageDto.ListResDto.builder()
                    .id(galleryImage.getId())
                    .deleted(galleryImage.getDeleted())
                    .createdAt(galleryImage.getCreatedAt())
                    .modifiedAt(galleryImage.getModifiedAt())
                    .imageUrl(galleryImage.getImageUrl())
                    .matchHistory(galleryImage.getMatchHistory())
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        String imageUrl;
        MatchHistory matchHistory;
    }

    // Update Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    // Delete Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }

}
