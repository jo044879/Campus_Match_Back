package com.pigs.holiday.controller;

import com.pigs.holiday.dto.MatchRequestDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.MatchRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/matchRequest")
@RestController
public class MatchRequestRestController {

    final MatchRequestService matchRequestService;

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    // Create
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{matchPostId}")
    public ResponseEntity<MatchRequestDto.CreateResDto> create(@PathVariable Long matchPostId, @RequestBody MatchRequestDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.create(matchPostId, createReqDto, getReqUserId(principalDetails)));
    }

}
