package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.MatchRequest;
import com.pigs.holiday.dto.MatchRequestDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import com.pigs.holiday.repository.MatchRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchRequestService {

    final MatchRequestRepository matchRequestRepository;
    final ClubRepository clubRepository;
    final MatchPostRepository matchPostRepository;

    // Create
    public MatchRequestDto.CreateResDto create(Long matchPostId ,MatchRequestDto.CreateReqDto createReqDto, Long requestClubId){
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchPost matchPost = matchPostRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchRequest matchRequest = createReqDto.toEntity();
        matchRequest.setSenderClub(club);
        matchRequest.setMatchPost(matchPost);

        return MatchRequestDto.CreateResDto.toCreateResDto(matchRequestRepository.save(matchRequest));
    }

}
