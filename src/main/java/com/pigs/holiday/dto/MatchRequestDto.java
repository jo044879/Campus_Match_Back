package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class MatchRequestDto {

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String location;
        String sportCategory;
        LocalDateTime date;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Club receiveClub;
        Club senderClub;
    }
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserID;

        public MatchRequest toEntity() {
            return MatchRequest.of(
                    getLocation(),
                    getSportCategory(),
                    getDate(),
                    getStartTime(),
                    getEndTime(),
                    getReceiveClub(),
                    getSenderClub()
            );
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto extends DefaultDto.BaseDto{
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
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String location;
        String sportCategory;
        LocalDateTime date;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Club receiveClub;
        Club senderClub;
        public static MatchRequestDto.DetailResDto toDetailResDto(MatchRequest matchRequest) {
            return DetailResDto.builder()
                    .id(matchRequest.getId())
                    .deleted(matchRequest.getDeleted())
                    .createdAt(matchRequest.getCreatedAt())
                    .modifiedAt(matchRequest.getModifiedAt())
                    .location(matchRequest.getLocation())
                    .sportCategory(matchRequest.getSportCategory())
                    .date(matchRequest.getDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .receiveClub(matchRequest.getReceiveClub())
                    .senderClub(matchRequest.getSenderClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String location;
        String sportCategory;
        LocalDateTime date;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Club receiveClub;
        Club senderClub;

        public static MatchRequestDto.ListResDto toListResDto(MatchRequest matchRequest) {
            return ListResDto.builder()
                    .id(matchRequest.getId())
                    .deleted(matchRequest.getDeleted())
                    .createdAt(matchRequest.getCreatedAt())
                    .modifiedAt(matchRequest.getModifiedAt())
                    .location(matchRequest.getLocation())
                    .sportCategory(matchRequest.getSportCategory())
                    .date(matchRequest.getDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .receiveClub(matchRequest.getReceiveClub())
                    .senderClub(matchRequest.getSenderClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequestDto extends DefaultDto.BaseDto{
        Long id;
        String location;
        String sportCategory;
        LocalDateTime date;
        LocalDateTime startTime;
        LocalDateTime endTime;
        Club receiveClub;
        Club senderClub;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateSevDto extends UpdateRequestDto {
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

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }
}




