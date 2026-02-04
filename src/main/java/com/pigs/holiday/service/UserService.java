package com.pigs.holiday.service;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.User;
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

        User user = userRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(user != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        user = userRepository.save(signupReqDto.toEntity());

        return user.toSignupResDto();
    }

}
