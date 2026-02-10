package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.MatchRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchRequestDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        Long senderClubId;
        LocalTime startTime;
        LocalTime endTime;

        public MatchRequest toEntity() {
            return MatchRequest.of(startTime, endTime, null, null);
        }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long matchRequestId;

        public static CreateResDto toCreateResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .build();
        }
    }

    // Dashboard List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardListResDto {
        Long matchRequestId;
        LocalDate matchDate;
        String university;
        String clubName;

        public static DashboardListResDto toDashboardReceiveListResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .university(matchRequest.getSenderClub().getUniversity())
                    .clubName(matchRequest.getSenderClub().getClubName())
                    .build();
        }

        public static DashboardListResDto toDashboardSendListResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .university(matchRequest.getMatchPost().getHomeClub().getUniversity())
                    .clubName(matchRequest.getMatchPost().getHomeClub().getClubName())
                    .build();
        }
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchRequestId;
        String region;
        String sportCategory;
        LocalDate matchDate;
        String location;
        String clubName;
        String university;
        Boolean myRequest;

        public static ListResDto toReceiveListResDto(MatchRequest matchRequest, Boolean myRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .region(matchRequest.getMatchPost().getHomeClub().getRegion())
                    .sportCategory(matchRequest.getMatchPost().getSportCategory())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .location(matchRequest.getMatchPost().getLocation())
                    .clubName(matchRequest.getSenderClub().getClubName())
                    .university(matchRequest.getSenderClub().getUniversity())
                    .myRequest(myRequest)
                    .build();
        }

        public static ListResDto toSendListResDto(MatchRequest matchRequest, Boolean myRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .region(matchRequest.getMatchPost().getHomeClub().getRegion())
                    .sportCategory(matchRequest.getMatchPost().getSportCategory())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .location(matchRequest.getMatchPost().getLocation())
                    .clubName(matchRequest.getMatchPost().getHomeClub().getClubName())
                    .university(matchRequest.getMatchPost().getHomeClub().getUniversity())
                    .myRequest(myRequest)
                    .build();
        }
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        LocalDate matchDate;
        LocalTime startTime;
        LocalTime endTime;
        String location;
        String phone;
        String content;

        public static DetailResDto toReceiveDetailResDto(MatchRequest matchRequest) {
            return builder()
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .location(matchRequest.getMatchPost().getLocation())
                    .phone(matchRequest.getSenderClub().getPhone())
                    .content(matchRequest.getMatchPost().getContent())
                    .build();
        }

        public static DetailResDto toSendDetailResDto(MatchRequest matchRequest) {
            return builder()
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .location(matchRequest.getMatchPost().getLocation())
                    .phone(matchRequest.getMatchPost().getHomeClub().getPhone())
                    .content(matchRequest.getMatchPost().getContent())
                    .build();
        }
    }

    // Delete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long matchRequestId;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long matchRequestId;
    }


}