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

    // ReceiveDashboardList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/receive/deshboard/{clubId}")
    public ResponseEntity<List<MatchRequestDto.DashboardListResDto>> receiveDashboardList(@PathVariable Long clubId){
        return ResponseEntity.ok(matchRequestService.receiveDashboardList(clubId));
    }

    // SendDashboardList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/send/dashboard/{clubId}")
    public ResponseEntity<List<MatchRequestDto.DashboardListResDto>> sendDashboardList(@PathVariable Long clubId){
        return ResponseEntity.ok(matchRequestService.sendDashboardList(clubId));
    }

    // ReceiveList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/receive/{clubId}")
    public ResponseEntity<List<MatchRequestDto.ListResDto>> receiveList(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.receiveList(clubId, getReqUserId(principalDetails)));
    }

    // ReceiveDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("receive/detail/{matchRequestId}")
    public ResponseEntity<MatchRequestDto.DetailResDto> receiveDetail(@PathVariable Long matchRequestId){
        return ResponseEntity.ok(matchRequestService.receiveDetail(matchRequestId));
    }

    // ReceiveDelete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("receive/{matchRequestId}")
    public ResponseEntity<MatchRequestDto.DeleteResDto> receiveDelete(@PathVariable Long matchRequestId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.receiveDelete(matchRequestId, getReqUserId(principalDetails)));
    }

    // ReceiveUpdate
    @PreAuthorize("hasRole('USER')")
    @PutMapping("receive/{matchRequestId}")
    public ResponseEntity<MatchRequestDto.UpdateResDto> receiveUpdate(@PathVariable Long matchRequestId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.receiveUpdate(matchRequestId, getReqUserId(principalDetails)));
    }

    // SendList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/send/{clubId}")
    public ResponseEntity<List<MatchRequestDto.ListResDto>> sendList(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.sendList(clubId, getReqUserId(principalDetails)));
    }

    // SendDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("send/detail/{matchRequestId}")
    public ResponseEntity<MatchRequestDto.DetailResDto> sendDetail(@PathVariable Long matchRequestId){
        return ResponseEntity.ok(matchRequestService.sendDetail(matchRequestId));
    }

    // SendDetail
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("send/delete/{matchRequestId}")
    public ResponseEntity<MatchRequestDto.DeleteResDto> sendDelete(@PathVariable Long matchRequestId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchRequestService.sendDelete(matchRequestId, getReqUserId(principalDetails)));
    }
}
