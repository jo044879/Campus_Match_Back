package com.pigs.holiday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import com.pigs.holiday.dto.ClubDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Club extends AuditingFields {
    String username;
    String password;
    String name;
    String university;
    String phone;
    String email;

    protected Club(){}
    private Club(String username, String password, String name, String university, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.university = university;
        this.phone = phone;
        this.email = email;
    }
    public static Club of(String username, String password, String name, String university, String phone, String email) { return new Club(username, password,  name, university, phone, email); }

    public ClubDto.SignupResDto toSignupResDto() { return ClubDto.SignupResDto.builder().id(getId()).build(); }
}
