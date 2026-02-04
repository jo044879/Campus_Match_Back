package com.pigs.holiday.service;

import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.repository.MatchPostRepository;
import com.pigs.holiday.service.admin.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchPostService {

    final String permission = "MatchPost";
    final RoleUserService roleUserService;
    final MatchPostRepository matchPostRepository;

    // Create
    public MatchPostDto.CreateResDto create(MatchPostDto.CreateSevDto createSevDto) {
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        return matchPostRepository.save(createSevDto.toEntity()).toCreateResDto();
    }

    // Detail
    public MatchPostDto.DetailResDto detail(MatchPostDto.DetailSevDto detailSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        return MatchPostDto.DetailResDto.toDetailResDto(matchPostRepository.findById(detailSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchPostService detail: No Data")));
    }

    // List
    public List<MatchPostDto.ListResDto> list(MatchPostDto.ListSevDto listSevDto){
        List<MatchPost> matchPostList = matchPostRepository.findAll();

        return matchPostList.stream().map(MatchPostDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(MatchPostDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        MatchPost MatchPost = matchPostRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchPostService update: No Data"));

        if(!updateSevDto.getSportCategory().isBlank()){
            MatchPost.setSportCategory(updateSevDto.getSportCategory());
        }
        if(updateSevDto.getMatchDate() != null){
            MatchPost.setMatchDate(updateSevDto.getMatchDate());
        }
        if(!updateSevDto.getLocation().isBlank()){
            MatchPost.setLocation(updateSevDto.getLocation());
        }
        if(!updateSevDto.getDescription().isBlank()){
            MatchPost.setDescription(updateSevDto.getDescription());
        }
        if(!updateSevDto.getStatus().isBlank()){
            MatchPost.setStatus(updateSevDto.getStatus());
        }
        if(updateSevDto.getClub() != null){
            MatchPost.setClub(updateSevDto.getClub());
        }
    }

    // Delete
    @Transactional
    public void delete(MatchPostDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        MatchPost MatchPost = matchPostRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("MatchPostService delete: No Data"));
        MatchPost.setDeleted(true);
    }
}
