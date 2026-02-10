package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.repository.ClubRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryService {

    final ClubRepository clubRepository;

//    // Create
//    public GalleryDto.CreateResDto create(GalleryDto.CreateReqDto createReqDto, List<String> imageUrls, Long requestClubId) {
//        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Create Error"));
//        Gallery gallery = createReqDto.toEntity();
//        gallery.setClub(club);
//
//        if (imageUrls != null) {
//            for (String url : imageUrls) {
//                GalleryImage galleryImage = GalleryImage.of(url, gallery);
//
//                galleryImageRepository.save(galleryImage);
//            }
//        }
//
//        return GalleryDto.CreateResDto.toCreateResDto(gallery);
//    }

}
