package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.dto.admin.RoleUserDto;
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
        List<Club> clubList = clubRepository.findAll();

        return clubList.stream().map(ClubDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(ClubDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        Club Club = clubRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubService update: No Data"));

        if(!updateSevDto.getName().isBlank()){
            Club.setName(updateSevDto.getName());
        }
        if(!updateSevDto.getUniversity().isBlank()){
            Club.setUniversity(updateSevDto.getUniversity());
        }
        if(!updateSevDto.getSportCategory().isBlank()){
            Club.setSportCategory(updateSevDto.getSportCategory());
        }
        if(!updateSevDto.getRegion().isBlank()){
            Club.setRegion(updateSevDto.getRegion());
        }
        if(!updateSevDto.getDescription().isBlank()){
            Club.setDescription(updateSevDto.getDescription());
        }
        if(!updateSevDto.getLogoUrl().isBlank()){
            Club.setLogoUrl(updateSevDto.getLogoUrl());
        }
        if(updateSevDto.getMannerScore()!=-1){
            Club.setMannerScore(updateSevDto.getMannerScore());
        }
        if(updateSevDto.getTotalWins()!=-1){
            Club.setTotalWins(updateSevDto.getTotalWins());
        }
        if(updateSevDto.getTotalMatches()!=-1){
            Club.setTotalMatches(updateSevDto.getTotalMatches());
        }
    }

    // Delete
    @Transactional
    public void delete(ClubDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        Club Club = clubRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubService delete: No Data"));
        Club.setDeleted(true);
    }
}
