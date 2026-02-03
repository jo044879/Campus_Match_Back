package com.pigs.holiday.dto.user;

import lombok.*;
import com.pigs.holiday.domain.user.User;

public class UserDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
        public String username;
        public String password;
        public String name;
        public String university;
        public String phone;
        public String email;

        public User toEntity() { return User.of(getUsername(), getPassword(), getName(), getUniversity(), getPhone(), getEmail()); }
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
