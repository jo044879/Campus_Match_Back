package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.RoleDto;

import java.util.List;

public interface RoleMapper {
    RoleDto.DetailResDto detail(RoleDto.DetailSevDto detailSevDto);
    List<RoleDto.ListResDto> list(RoleDto.ListSevDto listSevDto);
}
