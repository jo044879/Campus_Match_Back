package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.MatchRequest;
import com.pigs.holiday.dto.MatchRequestDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import com.pigs.holiday.repository.MatchRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchRequestService {

    final MatchRequestRepository matchRequestRepository;
    final ClubRepository clubRepository;
    final MatchPostRepository matchPostRepository;

    // Create
    public MatchRequestDto.CreateResDto create(Long matchPostId, MatchRequestDto.CreateReqDto createReqDto, Long requestClubId){
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchRequest matchRequest = createReqDto.toEntity();
        matchRequest.setSenderClub(club);
        matchRequest.setMatchPost(matchPost);

        return MatchRequestDto.CreateResDto.toCreateResDto(matchRequestRepository.save(matchRequest));
    }

    // ReceiveDashboardList
    public List<MatchRequestDto.DashboardListResDto> receiveDashboardList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDashboardList Error"));
        List<MatchPost> matchPostList = matchPostRepository.findByHomeClubAndStatusAndDeleted(club, false, false);

        List<MatchRequest> matchRequestList = new ArrayList<>();

        for (MatchPost matchPost : matchPostList) {
            matchRequestList.addAll(matchPost.getMatchRequestList());
        }

        return matchRequestList.stream().map(MatchRequestDto.DashboardListResDto::toDashboardReceiveListResDto).toList();
    }

    // SendDashboardList
    public List<MatchRequestDto.DashboardListResDto> sendDashboardList(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest SendDashboardList Error"));
        List<MatchRequest> matchRequestList = matchRequestRepository.findBySenderClub(club);

        return matchRequestList.stream().map(MatchRequestDto.DashboardListResDto::toDashboardSendListResDto).toList();
    }

    // ReceiveList
    public List<MatchRequestDto.ListResDto> receiveList(Long clubId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveList Error"));
        List<MatchPost> matchPostList = matchPostRepository.findByHomeClubAndStatusAndDeleted(club, false, false);

        List<MatchRequest> matchRequestList = new ArrayList<>();

        for (MatchPost matchPost : matchPostList) {
            matchRequestList.addAll(matchPost.getMatchRequestList());
        }

        return matchRequestList.stream().map(matchRequest -> MatchRequestDto.ListResDto.toReceiveListResDto(matchRequest, clubId.equals(requestClubId))).toList();
    }

    // ReceiveDetail
    public MatchRequestDto.DetailResDto receiveDetail(Long matchRequestId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDetail Error"));
        return MatchRequestDto.DetailResDto.toReceiveDetailResDto(matchRequest);
    }

    // ReceiveDelete
    @Transactional
    public MatchRequestDto.DeleteResDto receiveDelete(Long matchRequestId, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDelete Error"));
        if(!matchRequest.getMatchPost().getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest ReceiveDelete Error");
        }

        matchRequest.setDeleted(true);

        // notification

        return MatchRequestDto.DeleteResDto.builder().matchRequestId(matchRequest.getId()).build();
    }

    // ReceiveUpdate
    @Transactional
    public MatchRequestDto.UpdateResDto receiveUpdate(Long matchRequestId, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveUpdate Error"));
        if(!matchRequest.getMatchPost().getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest ReceiveUpdate Error");
        }

        MatchPost matchPost = matchRequest.getMatchPost();
        matchPost.setAwayClub(matchRequest.getSenderClub());
        matchPost.setStartTime(matchRequest.getStartTime());
        matchPost.setEndTime(matchRequest.getEndTime());
        matchPost.setStatus(true);

        matchRequestRepository.deleteByMatchPost(matchPost);

        // notification

        return MatchRequestDto.UpdateResDto.builder().matchRequestId(matchRequest.getId()).build();
    }

    // SendList
    public List<MatchRequestDto.ListResDto> sendList(Long clubId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest SendDashboardList Error"));
        List<MatchRequest> matchRequestList = matchRequestRepository.findBySenderClub(club);

        return matchRequestList.stream().map(matchRequest -> MatchRequestDto.ListResDto.toSendListResDto(matchRequest, clubId.equals(requestClubId))).toList();
    }

    // SendDetail
    public MatchRequestDto.DetailResDto sendDetail(Long matchRequestId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDetail Error"));
        return MatchRequestDto.DetailResDto.toSendDetailResDto(matchRequest);
    }

    // SendDelete
    public MatchRequestDto.DeleteResDto sendDelete(Long matchRequestId, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDelete Error"));
        if(!matchRequest.getSenderClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest ReceiveDelete Error");
        }

        // notification

        matchRequestRepository.deleteById(matchRequestId);
        return MatchRequestDto.DeleteResDto.builder().matchRequestId(matchRequest.getId()).build();

    }
}
