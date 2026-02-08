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
}
