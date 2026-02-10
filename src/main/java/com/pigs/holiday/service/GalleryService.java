package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.GalleryImageRepository;
import com.pigs.holiday.repository.GalleryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryService {

    final ClubRepository clubRepository;
    final GalleryRepository galleryRepository;
    final GalleryImageRepository galleryImageRepository;

    // Create
    public GalleryDto.CreateResDto create(GalleryDto.CreateReqDto createReqDto, List<String> imageUrls, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Create Error"));
        Gallery gallery = createReqDto.toEntity();
        gallery.setClub(club);

        galleryRepository.save(gallery);

        if (imageUrls != null) {
            for (String url : imageUrls) {
                GalleryImage galleryImage = GalleryImage.of(url, gallery);

                galleryImageRepository.save(galleryImage);
            }
        }

        return GalleryDto.CreateResDto.toCreateResDto(gallery);
    }

    // List
    public List<GalleryDto.ListResDto> list(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Gallery List Error"));
        List<Gallery> galleryList = galleryRepository.findByClub(club);

        return galleryList.stream().map(GalleryDto.ListResDto::toListResDto).toList();
    }

    // MatchList
    public List<GalleryDto.ListResDto> matchList(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Gallery MyClubList Error"));
        List<Gallery> galleryList = galleryRepository.findByClubAndIsOfficial(club, true);

        return galleryList.stream().map(GalleryDto.ListResDto::toListResDto).toList();
    }

    // MyClubList
    public List<GalleryDto.ListResDto> myClubList(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Gallery MyClubList Error"));
        List<Gallery> galleryList = galleryRepository.findByClubAndIsOfficial(club, false);

        return galleryList.stream().map(GalleryDto.ListResDto::toListResDto).toList();
    }

    // Detail
    public GalleryDto.DetailResDto detail(Long galleryId, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Detail Error"));
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Detail Error"));

        return GalleryDto.DetailResDto.toDetailResDto(gallery, gallery.getClub().equals(club));
    }

    // Update
    @Transactional
    public GalleryDto.UpdateResDto update(Long galleryId, GalleryDto.UpdateReqDto updateReqDto, List<String> imageUrls, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Update Error"));
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Update Error"));

        if(!gallery.getClub().equals(club)) {
            throw new NoPermissionException("Gallery Update Error");
        }

        if(!updateReqDto.getTitle().isBlank()){
            gallery.setTitle(updateReqDto.getTitle());
        }
        if(updateReqDto.getMatchDate()!=null){
            gallery.setMatchDate(updateReqDto.getMatchDate());
        }

        if (imageUrls != null) {
            List<GalleryImage> currentImages = gallery.getGalleryImageList();

            List<GalleryImage> imagesToDelete = currentImages.stream()
                    .filter(img -> !imageUrls.contains(img.getImageUrl()))
                    .toList();

            galleryImageRepository.deleteAll(imagesToDelete);
            currentImages.removeAll(imagesToDelete);

            for (String url : imageUrls) {
                boolean isExist = currentImages.stream()
                        .anyMatch(img -> img.getImageUrl().equals(url));

                if (!isExist) {
                    GalleryImage newImage = GalleryImage.of(url, gallery);
                    galleryImageRepository.save(newImage);
                }
            }
        }

        return GalleryDto.UpdateResDto.toUpdateResDto(gallery);
    }

    // Delete
    public GalleryDto.DeleteResDto delete(Long galleryId, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Delete Error"));
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Delete Error"));

        if(!gallery.getClub().equals(club)) {
            throw new NoPermissionException("Gallery Delete Error");
        }

        galleryRepository.delete(gallery);

        return GalleryDto.DeleteResDto.toDeleteResDto(gallery);
    }
}
