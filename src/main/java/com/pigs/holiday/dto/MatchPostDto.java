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

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchPostId;
        String region;
        String sportCategory;
        LocalDate matchDate;
        String location;
        String ClubName;
        String university;
        int mannerScore;

        public static ListResDto toListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .region(matchPost.getHomeClub().getRegion())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .ClubName(matchPost.getHomeClub().getClubName())
                    .university(matchPost.getHomeClub().getUniversity())
                    .mannerScore(matchPost.getHomeClub().getMannerScore())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String region;
        String sportCategory;
        LocalDate matchDate;
        String location;
        String ClubName;
        String university;
        int mannerScore;
        String startTime;
        String endTime;
        String content;
        Boolean myClub;

        public static DetailResDto toDetailResDto(MatchPost matchPost) {
            return builder()
                    .region(matchPost.getHomeClub().getRegion())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .ClubName(matchPost.getHomeClub().getClubName())
                    .university(matchPost.getHomeClub().getUniversity())
                    .mannerScore(matchPost.getHomeClub().getMannerScore())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .content(matchPost.getContent())
                    .build();
        }
    }
}
