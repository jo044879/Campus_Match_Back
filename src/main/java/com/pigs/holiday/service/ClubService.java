package com.pigs.holiday.service;

import com.pigs.holiday.domain.ClubTest;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.service.admin.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    final String permission = "Club";
    final RoleUserService roleUserService;
    final ClubRepository clubRepository;

    // Create
    public ClubDto.CreateResDto create(ClubDto.CreateSevDto createSevDto) {
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        return clubRepository.save(createSevDto.toEntity()).toCreateResDto();
    }

    // Detail
    public ClubDto.DetailResDto detail(ClubDto.DetailSevDto detailSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        return ClubDto.DetailResDto.toDetailResDto(clubRepository.findById(detailSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubService detail: No Data")));
    }

    // List
    public List<ClubDto.ListResDto> list(ClubDto.ListSevDto listSevDto){
        List<ClubTest> clubTestList = clubRepository.findAll();

        return clubTestList.stream().map(ClubDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(ClubDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        ClubTest ClubTest = clubRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubService update: No Data"));

        if(!updateSevDto.getName().isBlank()){
            ClubTest.setName(updateSevDto.getName());
        }
        if(!updateSevDto.getUniversity().isBlank()){
            ClubTest.setUniversity(updateSevDto.getUniversity());
        }
        if(!updateSevDto.getSportCategory().isBlank()){
            ClubTest.setSportCategory(updateSevDto.getSportCategory());
        }
        if(!updateSevDto.getRegion().isBlank()){
            ClubTest.setRegion(updateSevDto.getRegion());
        }
        if(!updateSevDto.getDescription().isBlank()){
            ClubTest.setDescription(updateSevDto.getDescription());
        }
        if(!updateSevDto.getLogoUrl().isBlank()){
            ClubTest.setLogoUrl(updateSevDto.getLogoUrl());
        }
        if(updateSevDto.getMannerScore()!=-1){
            ClubTest.setMannerScore(updateSevDto.getMannerScore());
        }
        if(updateSevDto.getTotalWins()!=-1){
            ClubTest.setTotalWins(updateSevDto.getTotalWins());
        }
        if(updateSevDto.getTotalMatches()!=-1){
            ClubTest.setTotalMatches(updateSevDto.getTotalMatches());
        }
    }

    // Delete
    @Transactional
    public void delete(ClubDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        ClubTest ClubTest = clubRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubService delete: No Data"));
        ClubTest.setDeleted(true);
    }
}
