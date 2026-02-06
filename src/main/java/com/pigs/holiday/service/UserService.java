package com.pigs.holiday.service;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.UserDto;
import com.pigs.holiday.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Signup
    public UserDto.SignupResDto signup(UserDto.SignupReqDto signupReqDto){

        Club club = userRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(club != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        club = userRepository.save(signupReqDto.toEntity());

        return club.toSignupResDto();
    }

}
