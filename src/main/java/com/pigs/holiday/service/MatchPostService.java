package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchPostService {

    final MatchPostRepository matchPostRepository;
    final ClubRepository clubRepository;

    // Create
    public MatchPostDto.CreateResDto create(MatchPostDto.CreateReqDto createReqDto, Long requestClubId){
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Create Error"));
        MatchPost matchPost = createReqDto.toEntity();
        matchPost.setHomeClub(club);

        return MatchPostDto.CreateResDto.toCreateResDto(matchPostRepository.save(matchPost));
    }

    // List
    public List<MatchPostDto.ListResDto> list(){
        List<MatchPost> matchPostList = matchPostRepository.findByDeletedAndStatus(false, false).orElseThrow(() -> new EntityNotFoundException("MatchPost List Error"));

        return matchPostList.stream().map(MatchPostDto.ListResDto::toListResDto).toList();
    }

    // Detail
    public MatchPostDto.DetailResDto detail(Long matchPostId, Long clubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Detail Error"));
        MatchPostDto.DetailResDto detailResDto = MatchPostDto.DetailResDto.toDetailResDto(matchPost);
        detailResDto.setMyClub(clubId.equals(matchPost.getHomeClub().getId()));

        return detailResDto;
    }

    // Update
    @Transactional
    public MatchPostDto.UpdateResDto update(Long matchPostId, MatchPostDto.UpdateReqDto updateReqDto, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Update Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId)){
            throw new NoPermissionException("MatchPost Update Error");
        }else if(matchPost.getStatus() || matchPost.getDeleted()){
            throw new RuntimeException("MatchPost Update Error");
        }

        if(!updateReqDto.getSportCategory().isBlank()){
            matchPost.setSportCategory(updateReqDto.getSportCategory());
        }
    }
}
