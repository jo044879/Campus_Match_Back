package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.MatchPost;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchPostDto {

    // Create Request Dto
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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

        public static ListResDto toHomeListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .region(matchPost.getHomeClub().getRegion())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .ClubName(matchPost.getAwayClub().getClubName())
                    .university(matchPost.getAwayClub().getUniversity())
                    .mannerScore(matchPost.getAwayClub().getMannerScore())
                    .build();
        }

        public static ListResDto toAwayListResDto(MatchPost matchPost) {
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
        Boolean myPost;

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
    public static class DashboardListResDto {
        Long matchPostId;
        LocalDate matchDate;
        String university;
        String clubName;

        public static DashboardListResDto toDashboardHomeListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .university(matchPost.getAwayClub().getUniversity())
                    .clubName(matchPost.getAwayClub().getClubName())
                    .build();
        }

        public static DashboardListResDto toDashboardAwayListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .university(matchPost.getHomeClub().getUniversity())
                    .clubName(matchPost.getHomeClub().getClubName())
                    .build();
        }
    }

    // IngDetail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class IngDetailResDto {
        LocalDate matchDate;
        LocalTime startTime;
        LocalTime endTime;
        String location;
        String phone;
        String content;
        Boolean myPost;

        public static IngDetailResDto toIngDetailHomeResDto(MatchPost matchPost) {
            return builder()
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .phone(matchPost.getAwayClub().getPhone())
                    .content(matchPost.getContent())
                    .build();
        }

        public static IngDetailResDto toIngDetailAwayResDto(MatchPost matchPost) {
            return builder()
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .phone(matchPost.getHomeClub().getPhone())
                    .content(matchPost.getContent())
                    .build();
        }
    }
}
