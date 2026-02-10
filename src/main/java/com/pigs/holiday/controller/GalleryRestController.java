package com.pigs.holiday.controller;

import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.FileService;
import com.pigs.holiday.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/gallery")
@RestController
public class GalleryRestController {

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    final GalleryService galleryService;
    private final FileService fileService;

//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("")
//    public ResponseEntity<GalleryDto.CreateResDto> create(@RequestBody GalleryDto.CreateReqDto createReqDto, @RequestPart("images") List<MultipartFile> files,  @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException{
//        //String s3Url = fileService.uploadFile(file, "likepigs/");
//        List<String> s3Urls = fileService.uploadFiles(files, "likepigs/");
//
//        return ResponseEntity.ok(galleryService.create(createReqDto, s3Url, getReqUserId(principalDetails)));
//    }
}
