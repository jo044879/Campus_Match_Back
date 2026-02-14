package com.pigs.holiday.dto;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import lombok.*;

import java.time.LocalDate;


public class MatchHistoryDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto {

        private Long clubId;
        private Long oppositionClubId;

        private LocalDate matchDate;
        private String location;
        private Boolean matchType;
        private String result;

        public MatchHistory toEntity(Club homeClub, Club awayClub) {
            return MatchHistory.of(
                    this.matchDate,
                    this.location,
                    true,
                    this.result,
                    homeClub,
                    awayClub
            );
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long reqId;

        public static MatchHistoryDto.CreateResDto toCreateResDto(MatchHistory matchHistory) {
            return builder()
                    .reqId(matchHistory.getId())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListReqDto{
        Long clubId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {

        Long matchHistoryId;
        LocalDate matchDate;
        String university;
        String clubName;
        String location;
        String result;


        public static MatchHistoryDto.ListResDto toListResDto(MatchHistory matchHistory) {
            return builder()
                    .matchHistoryId(matchHistory.getId())
                    .matchDate(matchHistory.getMatchDate())
                    .university(matchHistory.getHomeClub().getUniversity())
                    .clubName(matchHistory.getHomeClub().getClubName())
                    .location(matchHistory.getLocation())
                    .result(matchHistory.getResult())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto {
        Long matchHistoryId;
        Long oppositionClubId;
        LocalDate matchDate;
        Boolean matchType;
        String result;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResDto {
        Long matchHistoryId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteReqDto {
        Long matchHistoryId;
    }

}
