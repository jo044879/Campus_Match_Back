package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class MatchHistoryDto {

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        LocalDateTime matchDate;
        int homeScore;
        int awayScore;
        String result;
        Boolean isOfficial;
        Club homeClub;
        Club awayClub;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public MatchHistory toEntity() { return MatchHistory.of(
                getMatchDate(),
                getHomeScore(),
                getAwayScore(),
                getResult(),
                getIsOfficial(),
                getHomeClub(),
                getAwayClub()
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
        LocalDateTime matchDate;
        int homeScore;
        int awayScore;
        String result;
        Boolean isOfficial;
        Club homeClub;
        Club awayClub;

        public static DetailResDto toDetailResDto(MatchHistory matchHistory) {
            return DetailResDto.builder()
                    .id(matchHistory.getId())
                    .deleted(matchHistory.getDeleted())
                    .createdAt(matchHistory.getCreatedAt())
                    .modifiedAt(matchHistory.getModifiedAt())
                    .matchDate(matchHistory.getMatchDate())
                    .homeScore(matchHistory.getHomeScore())
                    .awayScore(matchHistory.getAwayScore())
                    .result(matchHistory.getResult())
                    .isOfficial(matchHistory.getIsOfficial())
                    .homeClub(matchHistory.getHomeClub())
                    .awayClub(matchHistory.getAwayClub())
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
        LocalDateTime matchDate;
        int homeScore;
        int awayScore;
        String result;
        Boolean isOfficial;
        Club homeClub;
        Club awayClub;

        public static ListResDto toListResDto(MatchHistory matchHistory) {
            return ListResDto.builder()
                    .id(matchHistory.getId())
                    .deleted(matchHistory.getDeleted())
                    .createdAt(matchHistory.getCreatedAt())
                    .modifiedAt(matchHistory.getModifiedAt())
                    .matchDate(matchHistory.getMatchDate())
                    .homeScore(matchHistory.getHomeScore())
                    .awayScore(matchHistory.getAwayScore())
                    .result(matchHistory.getResult())
                    .isOfficial(matchHistory.getIsOfficial())
                    .homeClub(matchHistory.getHomeClub())
                    .awayClub(matchHistory.getAwayClub())
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        LocalDateTime matchDate;
        int homeScore = -1;
        int awayScore = -1;
        String result;
        Boolean isOfficial;
        Club homeClub;
        Club awayClub;
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
