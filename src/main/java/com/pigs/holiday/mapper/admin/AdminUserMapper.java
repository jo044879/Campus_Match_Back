package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.AdminUserDto;

import java.util.List;

public interface AdminUserMapper {
    AdminUserDto.DetailResDto detail(AdminUserDto.DetailSevDto detailSevDto);
    List<AdminUserDto.ListResDto> list(AdminUserDto.ListSevDto listSevDto);
}
