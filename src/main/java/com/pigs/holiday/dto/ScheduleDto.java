package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Schedule;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        Long id;
        String title;
        LocalDate startDate;
        LocalDate endDate;
        private Club club;

        public static ListResDto toListresDto(Schedule schedule) {
            return builder()
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .club(schedule.getClub())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        private Club club;

        public static DetailResDto toDetailResDto(Schedule schedule) {
            return builder()
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .club(schedule.getClub())

                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto{
        Long id;
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResDto extends UpdateReqDto {
        Long reqId;
    }



    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteResDto {
        Long reqId;
    }

}
