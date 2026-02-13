package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Achievement;

import lombok.*;


public class AchievementDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        private String title;
        private String imageUrl;

        public static ListResDto from(Achievement achievement) {
            return ListResDto.builder()
                    .title(achievement.getTitle())
                    .imageUrl(achievement.getImageUrl())
                    .build();
        }
    }
}
