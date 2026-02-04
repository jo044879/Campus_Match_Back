package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.ClubMember;
import com.pigs.holiday.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class ClubMemberDto {

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        String role;
        String status;
        Club club;
        User user;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public ClubMember toEntity() { return ClubMember.of(
                getRole(),
                getStatus(),
                getClub(),
                getUser()
        ); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    // Detail Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Detail Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailSevDto extends DetailReqDto {
        Long reqUserId;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String role;
        String status;
        Club club;
        User user;

        public static ClubMemberDto.DetailResDto toDetailResDto(ClubMember clubMember) {
            return ClubMemberDto.DetailResDto.builder()
                    .id(clubMember.getId())
                    .deleted(clubMember.getDeleted())
                    .createdAt(clubMember.getCreatedAt())
                    .modifiedAt(clubMember.getModifiedAt())
                    .role(clubMember.getRole())
                    .status(clubMember.getStatus())
                    .club(clubMember.getClub())
                    .user(clubMember.getUser())
                    .build();
        }
    }

    // List Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListSevDto {
        Long reqUserId;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long id;
        Boolean deleted;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt;
        String role;
        String status;
        Club club;
        User user;

        public static ClubMemberDto.ListResDto toListResDto(ClubMember clubMember) {
            return ClubMemberDto.ListResDto.builder()
                    .id(clubMember.getId())
                    .deleted(clubMember.getDeleted())
                    .createdAt(clubMember.getCreatedAt())
                    .modifiedAt(clubMember.getModifiedAt())
                    .role(clubMember.getRole())
                    .status(clubMember.getStatus())
                    .club(clubMember.getClub())
                    .user(clubMember.getUser())
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.BaseDto {
        Long id;
        String role;
        String status;
        Club club;
        User user;
    }

    // Update Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    // Delete Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto extends DefaultDto.BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }

}
