package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.domain.Notification;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class NotificationDto {

    // Check Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CheckResDto {
        Boolean isNew;

        public static CheckResDto toCheckResDto(Boolean isNew) {
            return builder().isNew(isNew).build();
        }
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        int defaultNoti;
        int sendNoti;
        int receiveNoti;
        int finishNoti;
        Long clubId;

        List<DetailResDto> detailResDtoList;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long notificationId;
        String notiType;
        String content;
        String awayClubName;
        String awayClubUniversity;
        Long awayClubId;

        public static DetailResDto toRematchDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .awayClubId(notification.getAwayClub().getId())
                    .build();
        }

        public static DetailResDto toRemindDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .build();
        }

        public static DetailResDto toScheduleDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .content(notification.getContent())
                    .build();
        }

        public static DetailResDto toMatchCancelDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .content(notification.getContent())
                    .build();
        }

        public static DetailResDto toSendYesDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .build();
        }

        public static DetailResDto toSendNoDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .content(notification.getContent())
                    .build();
        }

        public static DetailResDto toReceiveDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .build();
        }

        public static DetailResDto toFinishDetailResDto(Notification notification) {
            return builder()
                    .notificationId(notification.getId())
                    .notiType(notification.getNotiType())
                    .awayClubName(notification.getAwayClub().getName())
                    .awayClubUniversity(notification.getAwayClub().getUniversity())
                    .build();
        }
    }

    // Delete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long notificationId;

        public static DeleteResDto toDeleteResDto(Notification notification) {
            return DeleteResDto.builder().notificationId(notification.getId()).build();
        }
    }
}
