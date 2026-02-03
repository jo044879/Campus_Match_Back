package com.pigs.holiday.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import com.pigs.holiday.domain.AuditingFields;
import com.pigs.holiday.dto.admin.AdminUserDto;
import com.pigs.holiday.dto.user.UserDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User extends AuditingFields {
    String username;
    String password;
    String name;
    String university;
    String phone;
    String email;

    protected User(){}
    private User(String username, String password, String name, String university, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.university = university;
        this.phone = phone;
        this.email = email;
    }
    public static User of(String username, String password, String name, String university, String phone, String email) { return new User(username, password,  name, university, phone, email); }

    public UserDto.SignupResDto toSignupResDto() { return UserDto.SignupResDto.builder().id(getId()).build(); }
    public AdminUserDto.CreateResDto toCreateResDto() { return AdminUserDto.CreateResDto.builder().id(getId()).build(); }
}
