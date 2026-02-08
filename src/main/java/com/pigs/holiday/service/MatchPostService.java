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
import java.util.ArrayList;
import java.util.Comparator;
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
        List<MatchPost> matchPostList = matchPostRepository.findByDeletedAndStatus(false, false);

        return matchPostList.stream().map(MatchPostDto.ListResDto::toListResDto).toList();
    }

    // Detail
    public MatchPostDto.DetailResDto detail(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Detail Error"));
        MatchPostDto.DetailResDto detailResDto = MatchPostDto.DetailResDto.toDetailResDto(matchPost);
        detailResDto.setMyPost(requestClubId.equals(matchPost.getHomeClub().getId()));

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
    public List<MatchPostDto.DashboardListResDto> upcomingDashboardList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndStatusAndMatchDateGreaterThan(club, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndStatusAndMatchDateGreaterThan(club, true, today);

        List<MatchPostDto.DashboardListResDto> dashboardListResDtoList = new ArrayList<>();

        dashboardListResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.DashboardListResDto::toDashboardHomeListResDto).toList());
        dashboardListResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.DashboardListResDto::toDashboardAwayListResDto).toList());

        dashboardListResDtoList.sort(Comparator.comparing(MatchPostDto.DashboardListResDto::getMatchDate));

        return dashboardListResDtoList;
    }

    // OngoingDashboard
    public List<MatchPostDto.DashboardListResDto> ongoingDashboardList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndStatusAndMatchDate(club, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndStatusAndMatchDate(club, true, today);

        List<MatchPostDto.DashboardListResDto> dashboardListResDtoList = new ArrayList<>();

        dashboardListResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.DashboardListResDto::toDashboardHomeListResDto).toList());
        dashboardListResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.DashboardListResDto::toDashboardAwayListResDto).toList());

        return dashboardListResDtoList;
    }

    // UpcomingList
    public List<MatchPostDto.ListResDto> upcomingList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingList Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndStatusAndMatchDateGreaterThan(club, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndStatusAndMatchDateGreaterThan(club, true, today);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();

        listResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        listResDtoList.sort(Comparator.comparing(MatchPostDto.ListResDto::getMatchDate));

        return listResDtoList;
    }

    // UpcomingDetail
    public MatchPostDto.IngDetailResDto upcomingDetail(Long clubId, Long matchPostId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            MatchPostDto.IngDetailResDto ingDetailResDto = MatchPostDto.IngDetailResDto.toIngHomeDetailResDto(matchPost);
            ingDetailResDto.setMyPost(clubId.equals(requestClubId));
            return ingDetailResDto;
        }else if(club.equals(matchPost.getAwayClub())){
            MatchPostDto.IngDetailResDto ingDetailResDto = MatchPostDto.IngDetailResDto.toIngAwayDetailResDto(matchPost);
            ingDetailResDto.setMyPost(clubId.equals(requestClubId));
            return ingDetailResDto;
        }else{
            throw new NoPermissionException("MatchPost UpcomingDetail Error");
        }
    }

    // UpcomingDelete
    @Transactional
    public MatchPostDto.DeleteResDto upcomingDelete(Long matchPostId,MatchPostDto.UpcomingDeleteReqDto upcomingDeleteReqDto, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDelete Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId) || !matchPost.getAwayClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost UpcomingDelete Error");
        }

        matchPost.setStatus(false);
        matchPost.setDeleted(true);

        // notification 생성

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // OngoingList
    public List<MatchPostDto.ListResDto> ongoingList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingList Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndStatusAndMatchDate(club, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndStatusAndMatchDate(club, true, today);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();

        listResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        return listResDtoList;
    }

    // OngoingDetail
    public MatchPostDto.IngDetailResDto ongoingDetail(Long clubId, Long matchPostId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            MatchPostDto.IngDetailResDto ingDetailResDto = MatchPostDto.IngDetailResDto.toIngHomeDetailResDto(matchPost);
            ingDetailResDto.setMyPost(clubId.equals(requestClubId));
            return ingDetailResDto;
        }else if(club.equals(matchPost.getAwayClub())){
            MatchPostDto.IngDetailResDto ingDetailResDto = MatchPostDto.IngDetailResDto.toIngAwayDetailResDto(matchPost);
            ingDetailResDto.setMyPost(clubId.equals(requestClubId));
            return ingDetailResDto;
        }else{
            throw new NoPermissionException("MatchPost OngoingDetail Error");
        }
    }

    // OngoingDelete
    @Transactional
    public MatchPostDto.DeleteResDto ongoingDelete(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDelete Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId) || !matchPost.getAwayClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost OngoingDelete Error");
        }

        matchPost.setDeleted(true);

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // FinishList
    @Transactional
    public List<MatchPostDto.ListResDto> finishList(Long requestClubId){
        LocalDate today = LocalDate.now();

        List<MatchPost> matchPostList = matchPostRepository.findByStatusAndDeletedAndMatchDateLessThan(true, false, today);
        for(MatchPost matchPost : matchPostList){
            matchPost.setDeleted(true);
        }

        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost FinishList Error"));
        List<MatchPost> HomelistResDtoList = matchPostRepository.findByHomeClubAndStatusAndDeleted(club, true, true);
        List<MatchPost> AwaylistResDtoList = matchPostRepository.findByAwayClubAndStatusAndDeleted(club, true, true);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();
        listResDtoList.addAll(HomelistResDtoList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(AwaylistResDtoList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        return listResDtoList;
    }

    // FinishDetail
    public MatchPostDto.FinishDetailResDto finishDetail(Long matchPostId, Long requestClubId){
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            return MatchPostDto.FinishDetailResDto.toFinishHomeDetailResDto(matchPost);
        }else if(club.equals(matchPost.getAwayClub())){
            return MatchPostDto.FinishDetailResDto.toFinishAwayDetailResDto(matchPost);
        }else{
            throw new NoPermissionException("MatchPost FinishDetail Error");
        }
    }
}
