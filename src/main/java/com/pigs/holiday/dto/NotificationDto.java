package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.domain.Notification;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class NotificationDto {

    // Check Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CheckResDto {
        Boolean isNew;

        public static CheckResDto toCheckResDto(Boolean isNew) {
            return builder().isNew(isNew).build();
        }
    }

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        int defaultNoti;
        int sendNoti;
        int receiveNoti;
        int finishNoti;

        List<DetailResDto> detailResDtoList;
    }

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String notiType;
        String content;
        Long clubId;

        public static DetailResDto toRematchDetailResDto(Notification notification) {
            return builder()
                    .notiType(notification.getNotiType())
                    .clubId(notification.getClub().getId())
                    .build();
        }

        public static DetailResDto toRemindDetailResDto(Notification notification) {
            return builder()
                    .build();
        }

        public static DetailResDto toScheduleDetailResDto(Notification notification) {
            return builder()
                    .notiType(notification.getNotiType())
                    .build();
        }

        public static DetailResDto toMatchCancelDetailResDto(Notification notification) {
            return builder()
                    .notiType(notification.getNotiType())
                    .content(notification.getContent())
                    .build();
        }
    }
}
