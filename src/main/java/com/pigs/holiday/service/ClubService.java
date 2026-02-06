package com.pigs.holiday.service;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClubService {

    final ClubRepository clubRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Signup
    public ClubDto.SignupResDto signup(ClubDto.SignupReqDto signupReqDto){

        Club club = clubRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(club != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        club = clubRepository.save(signupReqDto.toEntity());

        return club.toSignupResDto();
    }

}
