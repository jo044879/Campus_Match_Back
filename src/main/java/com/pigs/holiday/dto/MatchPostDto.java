package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.MatchPost;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchPostDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
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

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchPostId;
        String region;
        String sportCategory;
        LocalDate matchDate;
        String location;
        String ClubName;
        String university;
        double mannerScore;

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

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String region;
        String sportCategory;
        LocalDate matchDate;
        String location;
        String ClubName;
        String university;
        double mannerScore;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
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

    // Update Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String content;
    }

    // Update Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long matchPostId;
    }

    // Delete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long matchPostId;
    }

    // Dashboard Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long matchPostId;
        LocalDate matchDate;
        String university;
        String clubName;

        public static DashboardResDto toDashboardResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .university(matchPost.getAwayClub().getUniversity())
                    .clubName(matchPost.getAwayClub().getClubName())
                    .build();
        }
    }

}
