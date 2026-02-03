package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.FaqDto;

import java.util.List;

public interface FaqMapper {
    FaqDto.DetailResDto detail(FaqDto.DetailSevDto detailSevDto);
    List<FaqDto.ListResDto> list(FaqDto.ListSevDto listSevDto);
}
