package com.pigs.holiday.controller;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<ClubDto.CreateResDto> create(@RequestBody ClubDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.create(createReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dashboard/{clubId}")
    public  ResponseEntity<ClubDto.DashboardDetailResDto> dashboardDetail(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.dashboardDetail(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<ClubDto.ListResDto>> list() {
        return ResponseEntity.ok(clubService.list());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/dashboard/{clubId}")
    public ResponseEntity<ClubDto.DashboardUpdateResDto> dashboardUpdate(@RequestBody ClubDto.DashboardUpdateReqDto dashboardUpdateReqDto, @PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.dashboardUpdate(dashboardUpdateReqDto, clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dashboard/{clubId}")
    public ResponseEntity<ClubDto.SettingDetailResDto> settingDetail(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.settingDetail(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/setting/{clubId}")
    public ResponseEntity<ClubDto.SettingUpdateResDto> settingUpdate (@RequestBody ClubDto.SettingUpdateReqDto settingUpdateReqDto, @PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.settingUpdate(settingUpdateReqDto, clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/setting/{clubId}")
    public ResponseEntity<ClubDto.SettingDeleteResDto> delete (@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.delete(clubId));
    }


}
