package com.pigs.holiday.service;

import com.pigs.holiday.domain.ClubMember;
import com.pigs.holiday.dto.ClubMemberDto;
import com.pigs.holiday.repository.ClubMemberRepository;
import com.pigs.holiday.service.admin.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubMemberService {

    final String permission = "ClubMember";
    final RoleUserService roleUserService;
    final ClubMemberRepository clubMemberRepository;

    // Create
    public ClubMemberDto.CreateResDto create(ClubMemberDto.CreateSevDto createSevDto) {
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        return clubMemberRepository.save(createSevDto.toEntity()).toCreateResDto();
    }

    // Detail
    public ClubMemberDto.DetailResDto detail(ClubMemberDto.DetailSevDto detailSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        return ClubMemberDto.DetailResDto.toDetailResDto(clubMemberRepository.findById(detailSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubMemberService detail: No Data")));
    }

    // List
    public List<ClubMemberDto.ListResDto> list(ClubMemberDto.ListSevDto listSevDto){
        List<ClubMember> clubMemberList = clubMemberRepository.findAll();

        return clubMemberList.stream().map(ClubMemberDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(ClubMemberDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        ClubMember ClubMember = clubMemberRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubMemberService update: No Data"));

        if(!updateSevDto.getRole().isBlank()){
            ClubMember.setRole(updateSevDto.getRole());
        }
        if(!updateSevDto.getStatus().isBlank()){
            ClubMember.setStatus(updateSevDto.getStatus());
        }
        if(updateSevDto.getClubTest()!=null){
            ClubMember.setClubTest(updateSevDto.getClubTest());
        }
        if(updateSevDto.getClub()!=null){
            ClubMember.setClub(updateSevDto.getClub());
        }

    }

    // Delete
    @Transactional
    public void delete(ClubMemberDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        ClubMember ClubMember = clubMemberRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("ClubMemberService delete: No Data"));
        ClubMember.setDeleted(true);
    }
}
