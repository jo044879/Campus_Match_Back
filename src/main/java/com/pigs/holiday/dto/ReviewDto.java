package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.domain.Review;
import com.pigs.holiday.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        int rating;
        String comment;
        MatchHistory matchHistory;
        Club reviewerClub;
        Club targetClub;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public Review toEntity() {
            return Review.of(
                    getRating(),
                    getComment(),
                    getMatchHistory(),
                    getReviewerClub(),
                    getTargetClub()
            );
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailSevDto extends DetailReqDto {
        Long reqUserId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        int rating;
        String comment;
        MatchHistory matchHistory;
        Club reviewerClub;
        Club targetClub;


        public static ReviewDto.DetailResDto toDetailResDto(Review review) {
            return DetailResDto.builder()
                    .id(review.getId())
                    .deleted(review.getDeleted())
                    .createdAt(review.getCreatedAt())
                    .modifiedAt(review.getModifiedAt())
                    .rating(review.getRating())
                    .comment(review.getComment())
                    .matchHistory(review.getMatchHistory())
                    .reviewerClub(review.getReviewerClub())
                    .targetClub(review.getTargetClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        int rating;
        String comment;
        MatchHistory matchHistory;
        Club reviewerClub;
        Club targetClub;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }

}
