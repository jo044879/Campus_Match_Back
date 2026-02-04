package com.pigs.holiday.service;

import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.dto.GalleryImageDto;
import com.pigs.holiday.repository.GalleryImageRepository;
import com.pigs.holiday.service.admin.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryImageService {

    final String permission = "GalleryImage";
    final RoleUserService roleUserService;
    final GalleryImageRepository galleryImageRepository;

    // Create
    public GalleryImageDto.CreateResDto create(GalleryImageDto.CreateSevDto createSevDto) {
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        return galleryImageRepository.save(createSevDto.toEntity()).toCreateResDto();
    }

    // Detail
    public GalleryImageDto.DetailResDto detail(GalleryImageDto.DetailSevDto detailSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        return GalleryImageDto.DetailResDto.toDetailResDto(galleryImageRepository.findById(detailSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("GalleryImageService detail: No Data")));
    }

    // List
    public List<GalleryImageDto.ListResDto> list(GalleryImageDto.ListSevDto listSevDto){
        List<GalleryImage> galleryImageList = galleryImageRepository.findAll();

        return galleryImageList.stream().map(GalleryImageDto.ListResDto::toListResDto).toList();
    }

    // Update
    @Transactional
    public void update(GalleryImageDto.UpdateSevDto updateSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        GalleryImage GalleryImage = galleryImageRepository.findById(updateSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("GalleryImageService update: No Data"));

        if(!updateSevDto.getImageUrl().isBlank()){
            GalleryImage.setImageUrl(updateSevDto.getImageUrl());
        }
        if(updateSevDto.getMatchHistory()!=null){
            GalleryImage.setMatchHistory(updateSevDto.getMatchHistory());
        }
    }

    // Delete
    @Transactional
    public void delete(GalleryImageDto.DeleteSevDto deleteSevDto){
        // roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        GalleryImage GalleryImage = galleryImageRepository.findById(deleteSevDto.getId()).orElseThrow(()-> new EntityNotFoundException("GalleryImageService delete: No Data"));
        GalleryImage.setDeleted(true);
    }
}
