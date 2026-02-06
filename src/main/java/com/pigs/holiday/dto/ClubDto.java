package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class ClubDto {

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String clubName;
        String description;
        String region;
        String sportCategory;
        String logoUrl;//이건 일단 남겨둠
        int mannerScore;
        int totalWins;
        int totalLosses;
        int totalMatches;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public Club toEntity() { return Club.of(
                getUsername(),
                getPassword(),
                getName(),
                getUniversity(),
                getEmail(),
                getPhone(),
                getClubName(),
                getDescription(),
                getSportCategory(),
                getRegion(),
                getLogoUrl(),
                getMannerScore(),
                getTotalWins(),
                getTotalMatches(),
                getTotalLosses()
        ); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    // Detail Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Detail Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailSevDto extends DetailReqDto {
        Long reqUserId;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String clubName;
        String description;
        String region;
        String sportCategory;
        String logoUrl;//이건 일단 남겨둠
        int mannerScore;
        int totalWins;
        int totalLosses;
        int totalMatches;

        public static DetailResDto toDetailResDto(Club club) {
            return DetailResDto.builder()
                    .id(club.getId())
                    .deleted(club.getDeleted())
                    .createdAt(club.getCreatedAt())
                    .modifiedAt(club.getModifiedAt())
                    .username(club.getUsername())
                    .password(club.getPassword())
                    .name(club.getName())
                    .university(club.getUniversity())
                    .phone(club.getPhone())
                    .email(club.getEmail())
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .region(club.getRegion())
                    .sportCategory(club.getSportCategory())
                    .logoUrl(club.getLogoUrl())
                    .mannerScore(club.getMannerScore())
                    .totalWins(club.getTotalWins())
                    .totalLosses(club.getTotalLosses())
                    .totalMatches(club.getTotalMatches())
                    .build();
        }
    }

    // List Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListSevDto { //findAll
        Long reqUserId;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String clubName;
        String description;
        String region;
        String sportCategory;
        String logoUrl;//이건 일단 남겨둠
        int mannerScore;
        int totalWins;
        int totalLosses;
        int totalMatches;

        public static ListResDto toListResDto(Club club) {
            return ListResDto.builder()
                    .id(club.getId())
                    .deleted(club.getDeleted())
                    .createdAt(club.getCreatedAt())
                    .modifiedAt(club.getModifiedAt())
                    .username(club.getUsername())
                    .password(club.getPassword())
                    .name(club.getName())
                    .university(club.getUniversity())
                    .phone(club.getPhone())
                    .email(club.getEmail())
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .region(club.getRegion())
                    .sportCategory(club.getSportCategory())
                    .logoUrl(club.getLogoUrl())
                    .mannerScore(club.getMannerScore())
                    .totalWins(club.getTotalWins())
                    .totalLosses(club.getTotalLosses())
                    .totalMatches(club.getTotalMatches())
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String clubName;
        String description;
        String region;
        String sportCategory;
        String logoUrl;//이건 일단 남겨둠
        int mannerScore = -1;
        int totalWins = -1;
        int totalLosses = -1;
        int totalMatches = -1;
    }

    // Update Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    // Delete Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }

}
