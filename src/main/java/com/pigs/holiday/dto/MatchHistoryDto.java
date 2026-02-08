package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import lombok.*;

import java.time.LocalDate;


public class MatchHistoryDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto{
        LocalDate matchDate;
        String location;
        boolean matchType;
        String result;
        private Club awayClub;
        private Club homeClub;

        public MatchHistory toEntity() {
            return MatchHistory.of(
                    getMatchDate(),
                    getLocation(),
                    true,
                    getResult(),
                    getHomeClub(),
                    getAwayClub()
            );
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto extends CreateReqDto{
        Long reqId;
    }

}
