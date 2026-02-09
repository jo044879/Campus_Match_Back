package com.pigs.holiday.service;


import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchHistoryService {

    final ClubRepository clubRepository;
    final MatchHistoryRepository matchHistoryRepository;

    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateReqDto createReqDto, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Create Error"));
        Club homeClub = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Home Club Not ffound"));

        Club awayClub = clubRepository.findById(createReqDto.getOppositionClubId())
                .orElseThrow(() -> new EntityNotFoundException("Away Club Not found"));
        MatchHistory matchHistory = createReqDto.toEntity(homeClub, awayClub);
        matchHistory.setHomeClub(club);
        return MatchHistoryDto.CreateResDto.toCreateResDto(matchHistoryRepository.save(matchHistory));
    }

    public List<MatchHistoryDto.ListResDto> list(Long clubId) {
        List<MatchHistory> matchesHistory = matchHistoryRepository.findByClubIdOrderByMatchDateDesc(clubId);
        return matchesHistory.stream().map(MatchHistoryDto.ListResDto::toListResDto).toList();
    }

    public MatchHistoryDto.UpdateResDto update(MatchHistoryDto.UpdateReqDto updateReqDto, Long matchHistoryId) {
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("HistoryUpdate Error"));

        matchHistory.setMatchDate(updateReqDto.getMatchDate());
        matchHistory.setLocation(updateReqDto.getLocation());
        matchHistory.setMatchType(true);
        matchHistory.setResult(updateReqDto.getResult());
        matchHistory.setHomeClub(updateReqDto.getHomeClub());
        matchHistory.setAwayClub(updateReqDto.getAwayClub());

        return MatchHistoryDto.UpdateResDto.builder().matchHistoryId(matchHistory.getId()).build();
    }

    public void delete(Long matchHistoryId) {
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("History delete Error"));
        matchHistoryRepository.deleteById(matchHistoryId);
    }

}
