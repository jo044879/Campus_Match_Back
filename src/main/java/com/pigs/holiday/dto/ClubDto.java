package com.pigs.holiday.dto;

import lombok.*;
import com.pigs.holiday.domain.Club;

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

        public Club toEntity() { return Club.of(getUsername(), getPassword(), getName(), getUniversity(), getPhone(), getEmail(), getClubName(), getDescription(), getRegion(), getSportCategory(), 0, 0, 0, 0, 80); }
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
}
