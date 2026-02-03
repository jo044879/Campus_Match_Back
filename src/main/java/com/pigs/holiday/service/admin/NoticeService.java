package com.pigs.holiday.service.admin;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.admin.Notice;
import com.pigs.holiday.dto.admin.NoticeDto;
import com.pigs.holiday.dto.admin.RoleUserDto;
import com.pigs.holiday.mapper.admin.NoticeMapper;
import com.pigs.holiday.repository.admin.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService{

    final String permission = "notice";
    final RoleUserService roleUserService;

    final NoticeRepository noticeRepository;
    final NoticeMapper noticeMapper;

    // Create
    public NoticeDto.CreateResDto create(NoticeDto.CreateSevDto createSevDto) {
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        NoticeDto.CreateResDto res = noticeRepository.save(createSevDto.toEntity()).toCreateResDto();

        return res;
    }

    // Detail
    public NoticeDto.DetailResDto detail(NoticeDto.DetailSevDto detailSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        NoticeDto.DetailResDto res = noticeMapper.detail(detailSevDto);

        return res;
    }

    // List
    public List<NoticeDto.ListResDto> list(NoticeDto.ListSevDto listSevDto){
        List<NoticeDto.ListResDto> res = noticeMapper.list(listSevDto);

        return res;
    }

    // Update
    public void update(NoticeDto.UpdateSevDto updateSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        Notice notice = noticeRepository.findById(updateSevDto.getId()).orElse(null);
        if(notice == null){
            throw new RuntimeException("no data");
        }

        if(updateSevDto.getTitle() != null){
            notice.setTitle(updateSevDto.getTitle());
        }
        if(updateSevDto.getContent() != null){
            notice.setContent(updateSevDto.getContent());
        }

        noticeRepository.save(notice);
    }

    // Delete
    public void delete(NoticeDto.DeleteSevDto deleteSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        Notice notice = noticeRepository.findById(deleteSevDto.getId()).orElse(null);
        if(notice == null){
            throw new RuntimeException("no data");
        }

        notice.setDeleted(true);

        noticeRepository.save(notice);
    }
}
