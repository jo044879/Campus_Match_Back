package com.pigs.holiday.mapper.admin;

import com.pigs.holiday.dto.admin.RoleUserDto;

import java.util.List;

public interface RoleUserMapper {
    long permit(RoleUserDto.PermitSevDto permitSevDto);
    List<RoleUserDto.ListResDto> userList(RoleUserDto.ListSevDto listSevDto);
    List<RoleUserDto.AddListResDto> addUserList(RoleUserDto.AddListSevDto addListSevDto);
    List<RoleUserDto.ListResDto> roleList(RoleUserDto.ListSevDto listSevDto);
    List<RoleUserDto.AddListResDto> addRoleList(RoleUserDto.AddListSevDto addListSevDto);
}
