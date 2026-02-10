package com.pigs.holiday.controller;

import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.ClubService;
import com.pigs.holiday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@RestController
public class ScheduleRestController {

    final ScheduleService scheduleService;
    private final ClubService clubService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{clubId}")
    public ResponseEntity<ScheduleDto.CreateResDto> create(@RequestBody ScheduleDto.CreateReqDto createReqDto, @PathVariable Long clubId) {
        return ResponseEntity.ok(scheduleService.create(createReqDto, clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<ScheduleDto.ListResDto>> list(@PathVariable Long clubId) {
        return ResponseEntity.ok(scheduleService.list(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<ScheduleDto.DetailResDto> detail(@PathVariable Long scheduleId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.detail(scheduleId, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/detail/{scheduleId}")
    public ResponseEntity<ScheduleDto.UpdateResDto> update(@RequestBody ScheduleDto.UpdateReqDto updateReqDto, @PathVariable Long scheduleId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.update(updateReqDto, scheduleId, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDto.DeleteResDto> delete(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.delete(scheduleId));
    }
}
