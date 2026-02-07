package com.pigs.holiday.controller;

import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.MatchPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/matchPost")
@RestController
public class MatchPostRestController {

    final MatchPostService matchPostService;

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<MatchPostDto.CreateResDto> signup(@RequestBody MatchPostDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.create(createReqDto, getReqUserId(principalDetails)));
    }
}
