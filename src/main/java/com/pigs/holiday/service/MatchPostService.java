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

import java.time.LocalDate;
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
    public MatchPostDto.DetailResDto detail(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Detail Error"));
        MatchPostDto.DetailResDto detailResDto = MatchPostDto.DetailResDto.toDetailResDto(matchPost);
        detailResDto.setMyClub(requestClubId.equals(matchPost.getHomeClub().getId()));

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
        if(updateReqDto.getMatchDate() != null){
            matchPost.setMatchDate(updateReqDto.getMatchDate());
        }
        if(!updateReqDto.getLocation().isBlank()){
            matchPost.setLocation(updateReqDto.getLocation());
        }
        if(updateReqDto.getStartTime() != null){
            matchPost.setStartTime(updateReqDto.getStartTime());
        }
        if(updateReqDto.getEndTime() != null){
            matchPost.setEndTime(updateReqDto.getEndTime());
        }
        if(!updateReqDto.getContent().isBlank()){
            matchPost.setContent(updateReqDto.getContent());
        }

        return MatchPostDto.UpdateResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // Delete
    public MatchPostDto.DeleteResDto delete(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Update Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost Update Error");
        }

        matchPostRepository.deleteById(matchPostId);

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // UpcomingDashboard
    public List<MatchPostDto.UpcomingDashboardResDto> upcomingDashboard(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostList = matchPostRepository.findByHomeClubAndStatusAndMatchDateGreaterThan(club, true, today).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        return matchPostList.stream().map(MatchPostDto.UpcomingDashboardResDto::toUpcomingDashboardResDto).toList();
    }
}
