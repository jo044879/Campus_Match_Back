package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.RefreshToken;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class RefreshTokenDto {
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        Long userId;
        String token;
    }

    // Create Service Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public RefreshToken toEntity() {
            return RefreshToken.of(
                    getToken(),
                    getUserId()
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

    // Detail Request Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Detail Service Dto
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
        Long userId;
        String token;

        public static RefreshTokenDto.DetailResDto toDetailResDto(RefreshToken refreshToken) {
            return DetailResDto.builder()
                    .id(refreshToken.getId())
                    .deleted(refreshToken.getDeleted())
                    .createdAt(refreshToken.getCreatedAt())
                    .modifiedAt(refreshToken.getModifiedAt())
                    .userId(refreshToken.getUserId())
                    .token(refreshToken.getToken())
                    .build();
        }
    }

    // List Service Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListSevDto {
        Long reqUserId;
    }

    // List Response Dto
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
        Long userId;
        String token;

        public static RefreshTokenDto.DetailResDto toListResDto(RefreshToken refreshToken) {
            return DetailResDto.builder()
                    .id(refreshToken.getId())
                    .deleted(refreshToken.getDeleted())
                    .createdAt(refreshToken.getCreatedAt())
                    .modifiedAt(refreshToken.getModifiedAt())
                    .userId(refreshToken.getUserId())
                    .token(refreshToken.getToken())
                    .build();
        }
    }

    // Update Request Dto
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        Long userId;
        String token;
    }

    // Update Service Dto
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
