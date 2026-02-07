package com.pigs.holiday.dto;

import com.pigs.holiday.domain.MatchPost;
import lombok.*;

import java.time.LocalDate;

public class MatchPostDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        String startTime;
        String endTime;
        String content;

        public MatchPost toEntity() {
            return MatchPost.of(getSportCategory(), getMatchDate(), getLocation(), getStartTime(), getEndTime(), getContent(), false, null, null);
        }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long matchPostId;

        public static CreateResDto toCreateResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .build();
        }
    }
}
