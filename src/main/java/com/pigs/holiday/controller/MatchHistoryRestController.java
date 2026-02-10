package com.pigs.holiday.controller;

import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.MatchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/matchHistory")
@RestController
public class MatchHistoryRestController {

    final MatchHistoryService matchHistoryService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("") // URL 경로 변수 없음
    public ResponseEntity<MatchHistoryDto.CreateResDto> create(@RequestBody MatchHistoryDto.CreateReqDto createReqDto) {
        return ResponseEntity.ok(matchHistoryService.create(createReqDto, createReqDto.getClubId()));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<MatchHistoryDto.ListResDto>> list(@RequestBody MatchHistoryDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(matchHistoryService.list(listReqDto.getClubId()));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<MatchHistoryDto.UpdateResDto> update(@RequestBody MatchHistoryDto.UpdateReqDto updateReqDto,  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(matchHistoryService.update(updateReqDto, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody MatchHistoryDto.DeleteReqDto deleteReqDto){
        matchHistoryService.delete(deleteReqDto.getMatchHistoryId());
        return ResponseEntity.ok().build();
    }

}
