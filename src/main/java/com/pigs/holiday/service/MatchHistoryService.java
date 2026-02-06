package com.pigs.holiday.service;

import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.repository.MatchHistoryRepository;
import com.pigs.holiday.service.admin.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchHistoryService {

    final String permission = "MatchHistory";
    final RoleUserService roleUserService;
    final MatchHistoryRepository matchHistoryRepository;

    // Create
    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateSevDto createSevDto) {
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        return matchHistoryRepository.save(createSevDto.toEntity()).toCreateResDto();
    }

    // Detail
    public MatchHistoryDto.DetailResDto detail(MatchHistoryDto.DetailSevDto detailSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        return MatchHistoryDto.DetailResDto.toDetailResDto(matchHistoryRepository.findById(detailSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchHistoryService detail: No Data")));
    }

    // List
    public List<MatchHistoryDto.ListResDto> list(MatchHistoryDto.ListSevDto listSevDto){
        List<MatchHistory> matchHistoryList = matchHistoryRepository.findAll();

        return matchHistoryList.stream().map(MatchHistoryDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(MatchHistoryDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        MatchHistory MatchHistory = matchHistoryRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchHistoryService update: No Data"));

        if(updateSevDto.getMatchDate() != null){
            MatchHistory.setMatchDate(updateSevDto.getMatchDate());
        }
        if(updateSevDto.getHomeScore() != -1){
            MatchHistory.setHomeScore(updateSevDto.getHomeScore());
        }
        if(updateSevDto.getAwayScore() != -1){
            MatchHistory.setAwayScore(updateSevDto.getAwayScore());
        }
        if(!updateSevDto.getResult().isBlank()){
            MatchHistory.setResult(updateSevDto.getResult());
        }
        if(updateSevDto.getIsOfficial() != null){
            MatchHistory.setIsOfficial(updateSevDto.getIsOfficial());
        }
        if(updateSevDto.getHomeClubTest() != null){
            MatchHistory.setHomeClubTest(updateSevDto.getHomeClubTest());
        }
        if(updateSevDto.getAwayClubTest() != null){
            MatchHistory.setAwayClubTest(updateSevDto.getAwayClubTest());
        }
    }

    // Delete
    @Transactional
    public void delete(MatchHistoryDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        MatchHistory MatchHistory = matchHistoryRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchHistoryService delete: No Data"));
        MatchHistory.setDeleted(true);
    }
}
