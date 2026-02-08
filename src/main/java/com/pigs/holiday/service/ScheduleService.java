package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Schedule;
import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ScheduleDto.UpdateResDto update(Long scheduleId, Long clubId, ScheduleDto.UpdateReqDto reqDto) { // reqDto 필수!

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule Update Error"));

        if (!schedule.getClub().getId().equals(clubId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        Schedule.of(
                reqDto.getTitle(),
                reqDto.getStartDate(),
                reqDto.getEndDate(),
                reqDto.getClub(),
                reqDto.getStartTime(),
                reqDto.getEndTime()
        );
        return new ScheduleDto.UpdateResDto(scheduleId);
    }

    @Transactional
    public ScheduleDto.DeleteResDto delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new EntityNotFoundException("delete Error"));
        scheduleRepository.delete(schedule);
        return ScheduleDto.DeleteResDto.builder().reqId(scheduleId).build();
    }
}
