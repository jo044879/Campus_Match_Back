package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.domain.MatchPost;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class MatchPostDto {

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String sportCategory;
        LocalDateTime matchDate;
        String location;
        String description;
        String status;
        Club club;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public MatchPost toEntity() { return MatchPost.of(
                getSportCategory(),
                getMatchDate(),
                getLocation(),
                getDescription(),
                getStatus(),
                getClub()
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
        String sportCategory;
        LocalDateTime matchDate;
        String location;
        String description;
        String status;
        Club club;

        public static MatchPostDto.DetailResDto toDetailResDto(MatchPost matchPost) {
            return MatchPostDto.DetailResDto.builder()
                    .id(matchPost.getId())
                    .deleted(matchPost.getDeleted())
                    .createdAt(matchPost.getCreatedAt())
                    .modifiedAt(matchPost.getModifiedAt())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .description(matchPost.getDescription())
                    .status(matchPost.getStatus())
                    .club(matchPost.getClub())
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
        String sportCategory;
        LocalDateTime matchDate;
        String location;
        String description;
        String status;
        Club club;

        public static MatchPostDto.ListResDto toListResDto(MatchPost matchPost) {
            return MatchPostDto.ListResDto.builder()
                    .id(matchPost.getId())
                    .deleted(matchPost.getDeleted())
                    .createdAt(matchPost.getCreatedAt())
                    .modifiedAt(matchPost.getModifiedAt())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .description(matchPost.getDescription())
                    .status(matchPost.getStatus())
                    .club(matchPost.getClub())
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        String sportCategory;
        LocalDateTime matchDate;
        String location;
        String description;
        String status;
        Club club;
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
