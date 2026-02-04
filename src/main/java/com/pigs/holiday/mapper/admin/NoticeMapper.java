package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.NoticeDto;

import java.util.List;

public interface NoticeMapper {
    NoticeDto.DetailResDto detail(NoticeDto.DetailSevDto detailSevDto);
    List<NoticeDto.ListResDto> list(NoticeDto.ListSevDto listSevDto);
}
