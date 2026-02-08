package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Schedule;
import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    final ScheduleRepository scheduleRepository;
    final ClubRepository clubRepository;

    public ScheduleDto.CreateResDto create(ScheduleDto.CreateReqDto createReqDto, Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("schedule Create Error"));
        Schedule schedule = createReqDto.toEntity();
        schedule.setClub(club);
        return ScheduleDto.CreateResDto.toCreateResDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleDto.ListResDto> list(Long clubId){
        List<Schedule> schedulesList = scheduleRepository.findByClubIdAndDeleted(clubId, false);
        return schedulesList.stream()
                .map(schedule -> {
                    ScheduleDto.ListResDto dto = ScheduleDto.ListResDto.toListresDto(schedule);
                    dto.setMyClub(true);
                    return dto;
                })
                .toList();
    }

    public ScheduleDto.DetailResDto detail(Long ScheduleId, Long clubId){
        Schedule schedule = scheduleRepository.findById(ScheduleId).orElseThrow(() -> new EntityNotFoundException("schedule Detail Error"));
        ScheduleDto.DetailResDto detailResDto = ScheduleDto.DetailResDto.toDetailResDto(schedule);
        detailResDto.setMyClub(clubId.equals(schedule.getClub().getId()));
        return detailResDto;
    }
/*
    public ScheduleDto.UpdateResDto update(Long id, Long clubId) {

    }

*/
}
