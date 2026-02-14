package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public static class CreateReqDto {
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;


        public Schedule toEntity(Club club) {
            return Schedule.of(
                    title,
                    startDate,
                    endDate,
                    club,
                    startTime,
                    endTime
                    );
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long scheduleId;

        public static ScheduleDto.CreateResDto toCreateResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        @JsonProperty("scheduleId")
        Long id;
        String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate;
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate;
        Boolean myClub;

        public static ListResDto from(Schedule schedule) {
            return ListResDto.builder()
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .myClub(true)
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
        Boolean myClub;


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
    public static class UpdateReqDto{
        Long id;
        String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate;
        @JsonFormat(pattern = "yyyy-MM-dd")
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
    public static class UpdateResDto {
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
