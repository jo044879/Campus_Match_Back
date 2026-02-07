package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchPostService {

    final MatchPostRepository matchPostRepository;
    final ClubRepository clubRepository;

    // Create
    public MatchPostDto.CreateResDto create(MatchPostDto.CreateReqDto createReqDto, Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Create Error: clubId"));
        MatchPost matchPost = createReqDto.toEntity();
        matchPost.setHomeClub(club);

        return MatchPostDto.CreateResDto.toCreateResDto(matchPostRepository.save(matchPost));
    }

}
