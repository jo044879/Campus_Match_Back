package com.pigs.holiday.service;


import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchHistoryService {

    final ClubRepository clubRepository;
    final MatchHistoryRepository matchHistoryRepository;

    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateReqDto createReqDto, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Create Error"));
        MatchHistory matchHistory = createReqDto.toEntity();
        matchHistory.setHomeClub(club);
        return MatchHistoryDto.CreateResDto.toCreateResDto(matchHistoryRepository.save(matchHistory));
    }
}
