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
    public ResponseEntity<List<MatchPostDto.ListResDto>> list(){
        return ResponseEntity.ok(matchPostService.list());
    }

    // Detail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.DetailResDto> detail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.detail(matchPostId, getReqUserId(principalDetails)));
    }

    // Update
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.UpdateResDto> update(@PathVariable Long matchPostId, @RequestBody MatchPostDto.UpdateReqDto updateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.update(matchPostId, updateReqDto, getReqUserId(principalDetails)));
    }

    // Delete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.DeleteResDto> delete(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.delete(matchPostId, getReqUserId(principalDetails)));
    }

    // UpcomingDashboard
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/upcoming/dashboard/{clubId}")
    public ResponseEntity<List<MatchPostDto.DashboardResDto>> upcomingDashboard(@PathVariable Long clubId){
        return ResponseEntity.ok(matchPostService.upcomingDashboard(clubId));
    }

    // UpcomingDashboard
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ongoing/dashboard/{clubId}")
    public ResponseEntity<List<MatchPostDto.DashboardResDto>> ongoingDashboard(@PathVariable Long clubId){
        return ResponseEntity.ok(matchPostService.ongoingDashboard(clubId));
    }
}
