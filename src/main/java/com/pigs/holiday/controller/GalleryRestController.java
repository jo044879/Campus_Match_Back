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
import java.util.List;

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

    // Create
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<GalleryDto.CreateResDto> create(@RequestBody GalleryDto.CreateReqDto createReqDto, @RequestPart("images") List<MultipartFile> files,  @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException{
        List<String> s3Urls = fileService.uploadFiles(files, "likepigs/");

        return ResponseEntity.ok(galleryService.create(createReqDto, s3Urls, getReqUserId(principalDetails)));
    }

    // List
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> list(@PathVariable Long clubId) {
        return ResponseEntity.ok(galleryService.list(clubId));
    }

    // MatchList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/match/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> matchList(@PathVariable Long clubId) {
        return ResponseEntity.ok(galleryService.matchList(clubId));
    }

    // MyClubList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/myClub/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> myClubList(@PathVariable Long clubId) {
        return ResponseEntity.ok(galleryService.myClubList(clubId));
    }

    // Detail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detail/{galleryId}")
    public ResponseEntity<GalleryDto.DetailResDto> detail(@PathVariable Long galleryId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(galleryService.detail(galleryId, getReqUserId(principalDetails)));
    }

    // Update
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{galleryId}")
    public ResponseEntity<GalleryDto.UpdateResDto> update(@PathVariable Long galleryId, @RequestBody GalleryDto.UpdateReqDto updateReqDto, @RequestPart("images") List<MultipartFile> files, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException{
        List<String> s3Urls = fileService.uploadFiles(files, "likepigs/");

        return ResponseEntity.ok(galleryService.update(galleryId, updateReqDto, s3Urls, getReqUserId(principalDetails)));
    }

    // Delete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{galleryId}")
    public ResponseEntity<GalleryDto.DeleteResDto> delete(@PathVariable Long galleryId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(galleryService.delete(galleryId, getReqUserId(principalDetails)));
    }
}
