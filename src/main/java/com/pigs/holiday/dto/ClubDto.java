package com.pigs.holiday.dto;

import lombok.*;
import com.pigs.holiday.domain.Club;
import lombok.experimental.SuperBuilder;

public class ClubDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
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

        public Club toEntity() { return Club.of(getUsername(), getPassword(), getName(), getUniversity(), getPhone(), getEmail(), getClubName(), getDescription(), getRegion(), getSportCategory(), 0, 0, 0, 0, 36.5); }
    }

    // Signup Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupResDto {
        Long id;
    }

    // Login Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        public String username;
        public String password;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public  static class CreateReqDto{
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
        int totalMatches;
        int totalWins;
        int totalDraws;
        int totalLosses;
        int mannerScore;

        public Club toEntity() {
            return Club.of(
                    getUsername(),
                    getPassword(),
                    getName(),
                    getUniversity(),
                    getPhone(),
                    getEmail(),
                    getClubName(),
                    getDescription(),
                    getRegion(),
                    getSportCategory(),
                    getTotalMatches(),
                    0,
                    0,
                    0,
                    36
            );
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long clubId;

        public static CreateResDto toCreateResDto(Club club) {
            return builder().clubId(club.getId()).build();
        }
    }


    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardDetailResDto {
        Long id;
        String clubName;
        String description;
        int totalMatches;
        int totalWins;
        int totalDraws;
        int totalLosses;
        int mannerScore;
        Boolean myClub;
        public static DashboardDetailResDto toDetailResDto(Club club) {
            return DashboardDetailResDto.builder()
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .totalMatches(club.getTotalMatches())
                    .totalWins(club.getTotalWins())
                    .totalDraws(club.getTotalDraws())
                    .totalLosses(club.getTotalLosses())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        String clubName;
        String description;
        int totalMatches;
        int totalWins;
        int totalDraws;
        int totalLosses;
        int mannerScore;

        public static ListResDto toListResDto(Club club) {
            return ListResDto.builder()
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .totalMatches(club.getTotalMatches())
                    .totalWins(club.getTotalWins())
                    .totalDraws(club.getTotalDraws())
                    .totalLosses(club.getTotalLosses())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardUpdateReqDto {
        String description;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardUpdateResDto {
        Long clubId;
    }


    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SettingDetailResDto {
        String clubName;
        String username;
        String password;
        String name;
        String university;
        String email;

        public static SettingDetailResDto toSettingDetailResDto(Club club) {
            return SettingDetailResDto.builder()
                    .clubName(club.getClubName())
                    .username(club.getUsername())
                    .password(club.getPassword())
                    .name(club.getName())
                    .university(club.getUniversity())
                    .email(club.getEmail())
                    .build();
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class SettingUpdateReqDto {
        String name;
        String username;
        String password;
        String university;
        String clubName;
        String email;

    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class SettingUpdateResDto{
        Long clubId;
    }



    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SettingDeleteResDto {
        Long clubId;
    }

    //동아리 온도 수정 미완
    /*
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class
    */
    //검색 페이지 미완














}
