package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class NotificationDto {

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String scheduledAt;
        String relatedUrl;
        Boolean isRead;
        private User user;
        private MatchPost matchPost;
        private Schedule schedule;
        private MatchRequest matchRequest;

    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserid;

        public Notification toEntity() {
            return Notification.of(
                    getScheduledAt(),
                    getRelatedUrl(),
                    getIsRead(),
                    getUser(),
                    getMatchPost(),
                    getSchedule(),
                    getMatchRequest()
            );
        }
    }

    @Getter
    @Setter
    @SuperBuilder
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
        String scheduledAt;
        String relatedUrl;
        Boolean isRead;
        private User user;
        private MatchPost matchPost;
        private Schedule schedule;
        private MatchRequest matchRequest;


        public static NotificationDto.DetailResDto toDetailResDto(Notification notification) {
            return DetailResDto.builder()
                    .id(notification.getId())
                    .deleted(notification.getDeleted())
                    .createdAt(notification.getCreatedAt())
                    .modifiedAt(notification.getModifiedAt())
                    .scheduledAt(notification.getScheduledAt())
                    .relatedUrl(notification.getRelatedUrl())
                    .isRead(notification.getIsRead())
                    .user(notification.getUser())
                    .matchPost(notification.getMatchPost())
                    .schedule(notification.getSchedule())
                    .matchRequest(notification.getMatchRequest())
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
        String scheduledAt;
        String relatedUrl;
        Boolean isRead;
        private User user;
        private MatchPost matchPost;
        private Schedule schedule;
        private MatchRequest matchRequest;

        public static ListResDto toListResDto(Notification notification) {
            return ListResDto.builder()
                    .id(notification.getId())
                    .deleted(notification.getDeleted())
                    .createdAt(notification.getCreatedAt())
                    .modifiedAt(notification.getModifiedAt())
                    .scheduledAt(notification.getScheduledAt())
                    .relatedUrl(notification.getRelatedUrl())
                    .isRead(notification.getIsRead())
                    .user(notification.getUser())
                    .matchPost(notification.getMatchPost())
                    .schedule(notification.getSchedule())
                    .matchRequest(notification.getMatchRequest())
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
        String scheduledAt;
        String relatedUrl;
        Boolean isRead;
        private User user;
        private MatchPost matchPost;
        private Schedule schedule;
        private MatchRequest matchRequest;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
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
