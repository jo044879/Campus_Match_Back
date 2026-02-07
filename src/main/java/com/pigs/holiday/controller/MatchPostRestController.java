package com.pigs.holiday.controller;

import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.MatchPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Create
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<MatchPostDto.CreateResDto> create(@RequestBody MatchPostDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.create(createReqDto, getReqUserId(principalDetails)));
    }

    // List
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<MatchPostDto.ListResDto>> list(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.list(getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.DetailResDto> Detail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.detail(matchPostId, getReqUserId(principalDetails)));
    }
}
