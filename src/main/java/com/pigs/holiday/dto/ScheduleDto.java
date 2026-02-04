package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Schedule;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class ScheduleDto {
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String title;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String location;
        private Club club;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public Schedule toEntity() {
            return Schedule.of(
                    getTitle(),
                    getStartDateTime(),
                    getEndDateTime(),
                    getLocation(),
                    getClub()
            );
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
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
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String title;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String location;
        private Club club;

        public static ScheduleDto.DetailResDto toDetailResDto(Schedule schedule) {
            return DetailResDto.builder()
                    .id(schedule.getId())
                    .deleted(schedule.getDeleted())
                    .createdAt(schedule.getCreatedAt())
                    .modifiedAt(schedule.getModifiedAt())
                    .title(schedule.getTitle())
                    .startDateTime(schedule.getStartDateTime())
                    .endDateTime(schedule.getEndDateTime())
                    .location(schedule.getLocation())
                    .club(schedule.getClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListSevDto {
        Long reqUserId;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String title;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String location;
        private Club club;

        public static ScheduleDto.ListResDto toListResDto(Schedule schedule) {
            return ScheduleDto.ListResDto.builder()
                    .id(schedule.getId())
                    .deleted(schedule.getDeleted())
                    .createdAt(schedule.getCreatedAt())
                    .modifiedAt(schedule.getModifiedAt())
                    .title(schedule.getTitle())
                    .startDateTime(schedule.getStartDateTime())
                    .endDateTime(schedule.getEndDateTime())
                    .location(schedule.getLocation())
                    .club(schedule.getClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        String title;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String location;
        private Club club;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    // Delete Request Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }
}
