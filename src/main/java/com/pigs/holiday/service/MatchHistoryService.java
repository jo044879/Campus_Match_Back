package com.pigs.holiday.service;


import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MatchHistoryService {

    final ClubRepository clubRepository;
    final MatchHistoryRepository matchHistoryRepository;

    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateReqDto createReqDto, Long clubId) {
        Club homeClub = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Home Club Not Found"));

        Club awayClub = clubRepository.findById(createReqDto.getOppositionClubId())
                .orElseThrow(() -> new EntityNotFoundException("Away Club Not Found"));

        MatchHistory matchHistory = createReqDto.toEntity(homeClub, awayClub);
        return MatchHistoryDto.CreateResDto.toCreateResDto(matchHistoryRepository.save(matchHistory));

    }

    public List<MatchHistoryDto.ListResDto> list(Long clubId) {
        List<MatchHistory> matchesHistory = matchHistoryRepository.findByClubId(clubId);
        return matchesHistory.stream()
                .map(MatchHistoryDto.ListResDto::toListResDto)
                .toList();
    }

    @Transactional
    public MatchHistoryDto.UpdateResDto update(MatchHistoryDto.UpdateReqDto reqDto, Long requestClubId) {

        MatchHistory matchHistory = matchHistoryRepository.findById(reqDto.getMatchHistoryId())
                .orElseThrow(() -> new EntityNotFoundException("MatchHistory Not Found"));

        if (!matchHistory.getHomeClub().getId().equals(requestClubId)) { throw new IllegalArgumentException("수정 권한이 없습니다.");}

        if (reqDto.getOppositionClubId() != null) {
            Club newOpponent = clubRepository.findById(reqDto.getOppositionClubId())
                    .orElseThrow(() -> new EntityNotFoundException("Opposition Club Not Found"));
            matchHistory.setAwayClub(newOpponent);
        }

        if (reqDto.getMatchDate() != null) {
            matchHistory.setMatchDate(reqDto.getMatchDate());
        }

        if (reqDto.getMatchType() != null) {
            matchHistory.setMatchType(reqDto.getMatchType());
        }

        if (reqDto.getResult() != null && !reqDto.getResult().isBlank()) {
            matchHistory.setResult(reqDto.getResult());
        }
        return MatchHistoryDto.UpdateResDto.builder()
                .matchHistoryId(matchHistory.getId())
                .build();
    }

    public void delete(Long matchHistoryId, Long clubId) {
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId)
                .orElseThrow(() -> new EntityNotFoundException("History Delete Error"));

        if (!matchHistory.getHomeClub().getId().equals(clubId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        matchHistory.setDeleted(true);
    }

}
