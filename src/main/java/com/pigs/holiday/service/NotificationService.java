//package com.pigs.holiday.service;
//
//import com.pigs.holiday.domain.Club;
//import com.pigs.holiday.domain.Notification;
//import com.pigs.holiday.dto.GalleryDto;
//import com.pigs.holiday.dto.NotificationDto;
//import com.pigs.holiday.repository.ClubRepository;
//import com.pigs.holiday.repository.NotificationRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class NotificationService {
//
//    final NotificationRepository notificationRepository;
//    final ClubRepository clubRepository;
//
//
//    // Check
//    public NotificationDto.CheckResDto check(Long requestClubId) {
//        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification Check Error"));
//
//        LocalDate today = LocalDate.now();
//
//        Boolean isNew = notificationRepository.existsByClubAndIsReadAndNotiDateLessThanEqual(club, false, today);
//
//        return NotificationDto.CheckResDto.toCheckResDto(isNew);
//    }
//
//
//    // DefaultList
//    public NotificationDto.ListResDto defaultList(Long requestClubId) {
//        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification DefaultList Error"));
//
//        LocalDate today = LocalDate.now();
//
//        NotificationDto.ListResDto listResDto = new NotificationDto.ListResDto();
//        listResDto.setDefaultNoti(notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "rematch", today)
//                + notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "remind", today)
//                + notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "schedule", today)
//                + notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "matchCancel", today));
//
//        listResDto.setSendNoti(notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendYes", today)
//                + notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendNo", today));
//
//        listResDto.setReceiveNoti(notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receive", today));
//
//        listResDto.setFinishNoti(notificationRepository.countByClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "finish", today));
//
//        List<NotificationDto.DetailResDto> detailResDtoList = new ArrayList<>();
//        detailResDtoList.addAll(notificationRepository.findByClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "rematch", today).stream().map(NotificationDto.DetailResDto::toRematchDetailResDto).toList());
//        detailResDtoList.addAll(notificationRepository.findByClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "remind", today).stream().map(NotificationDto.DetailResDto::toRematchDetailResDto).toList());
//        detailResDtoList.addAll(notificationRepository.findByClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "schedule", today).stream().map(NotificationDto.DetailResDto::toScheduleDetailResDto).toList());
//        detailResDtoList.addAll(notificationRepository.findByClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "matchCancel", today).stream().map(NotificationDto.DetailResDto::toMatchCancelDetailResDto).toList());
//
//        listResDto.setDetailResDtoList(detailResDtoList);
//
//        return listResDto;
//    }
//
//    // SendList
//    public NotificationDto.ListResDto sendList(Long requestClubId) {
//        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification DefaultList Error"));
//
//    }
//}
