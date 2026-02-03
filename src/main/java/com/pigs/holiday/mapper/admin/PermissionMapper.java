package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.PermissionDto;

import java.util.List;

public interface PermissionMapper {
    List<PermissionDto.ListResDto> list(PermissionDto.ListSevDto listSevDto);
}
