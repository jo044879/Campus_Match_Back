package com.pigs.holiday.controller;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/club")
@RestController
public class ClubRestController {

    final ClubService clubService;

    @PostMapping("/signup")
    public ResponseEntity<ClubDto.SignupResDto> signup(@RequestBody ClubDto.SignupReqDto signupReqDto){
        return ResponseEntity.ok(clubService.signup(signupReqDto));
    }

    //@PreAuthorize("hasRole('USER')")
}
