package com.pigs.holiday.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    final ClubRepository clubRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // Signup
    public ClubDto.SignupResDto signup(ClubDto.SignupReqDto signupReqDto){

        Club club = clubRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(club != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        club = clubRepository.save(signupReqDto.toEntity());

        return club.toSignupResDto();
    }

    @Transactional
    public ClubDto.CreateResDto create(ClubDto.CreateReqDto createReqDto) {
        Club club = createReqDto.toEntity();
        return ClubDto.CreateResDto.toCreateResDto(clubRepository.save(club));
    }

    @Transactional
    public ClubDto.DashboardDetailResDto dashboardDetail(ClubDto.DashboardDetailReqDto dashboardDetail, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("clubDetail Error"));
        ClubDto.DashboardDetailResDto detailResDto = ClubDto.DashboardDetailResDto.toDetailResDto(club);
        detailResDto.setMyClub(clubId.equals(club.getId()));
        return detailResDto;
    }

    @Transactional
    public List<ClubDto.ListResDto> list(Long clubId) {
        List<Club> clubList = clubRepository.findByDeleted(false).orElseThrow(() -> new EntityNotFoundException("clubDetail Error"));
        return clubList.stream().map(ClubDto.ListResDto :: toListResDto).toList();
    }

    //save를
    @Transactional
    public ClubDto.DashboardUpdateResDto dashboardUpdate(ClubDto.DashboardUpdateReqDto dashboardUpdate, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("clubUpdate Error"));
        club.setDescription(dashboardUpdate.getDescription());
        return ClubDto.DashboardUpdateResDto.builder().clubId(club.getId()).build();
    }


    @Transactional
    public ClubDto.SettingDetailResDto settingDetail(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("clubDetail Error"));
        return ClubDto.SettingDetailResDto.toSettingDetailResDto(club);
    }

    @Transactional
    public ClubDto.SettingUpdateResDto settingUpdate(ClubDto.SettingUpdateReqDto settingUpdateReqDto, Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Setting update Error: Club ID " + clubId + " not found"));

        club.setUsername(settingUpdateReqDto.getUsername());
        club.setPassword(settingUpdateReqDto.getPassword());
        club.setName(settingUpdateReqDto.getName());
        club.setUniversity(settingUpdateReqDto.getUniversity());
        club.setPhone(settingUpdateReqDto.getPhone());
        club.setEmail(settingUpdateReqDto.getEmail());
        club.setClubName(settingUpdateReqDto.getClubName());

        return ClubDto.SettingUpdateResDto.builder()
                .clubId(club.getId())
                .username(club.getUsername())
                .password(club.getPassword())
                .name(club.getName())
                .university(club.getUniversity())
                .phone(club.getPhone())
                .email(club.getEmail())
                .clubName(club.getClubName())
                .build();
    }


    @Transactional
    public ClubDto.SettingDeleteResDto delete(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("delete Error"));
        clubRepository.delete(club);
        return ClubDto.SettingDeleteResDto.builder()
                .clubId(clubId)
                .build();
    }





}
