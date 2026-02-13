//package com.pigs.holiday.controller;
//
//import com.pigs.holiday.dto.NotificationDto;
//import com.pigs.holiday.security.PrincipalDetails;
//import com.pigs.holiday.service.NotificationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RequestMapping("/api/notification")
//@RestController
//public class NotificationRestController {
//
//    public Long getReqUserId(PrincipalDetails principalDetails) {
//        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
//            return null;
//        }
//
//        return principalDetails.getUser().getId();
//    }
//
//    final NotificationService notificationService;
//
//    // Check
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/noti")
//    public ResponseEntity<NotificationDto.CheckResDto> check(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return ResponseEntity.ok(notificationService.check(getReqUserId(principalDetails)));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/noti/default")
//    public ResponseEntity<NotificationDto.ListResDto> defaultList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return ResponseEntity.ok(notificationService.defaultList(getReqUserId(principalDetails)));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/noti/send")
//    public ResponseEntity<NotificationDto.ListResDto> sendList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return ResponseEntity.ok(notificationService.sendList(getReqUserId(principalDetails)));
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/noti/receive")
//    public ResponseEntity<NotificationDto.ListResDto> receiveList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return ResponseEntity.ok(notificationService.receiveList());
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/noti/finish")
//    public ResponseEntity<NotificationDto.ListResDto> finishList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return ResponseEntity.ok(notificationService.finishList());
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @DeleteMapping("/setting/{notificationId}")
//    public ResponseEntity<NotificationDto.SettingDeleteResDto> delete (@PathVariable Long notificationId) {
//        return ResponseEntity.ok(notificationService.delete(notificationId));
//    }
//
//
//}
