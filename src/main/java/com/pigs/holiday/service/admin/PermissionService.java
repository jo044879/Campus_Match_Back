package com.pigs.holiday.service.admin;

import com.pigs.holiday.domain.admin.Permission;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.dto.admin.PermissionDto;
import com.pigs.holiday.mapper.admin.PermissionMapper;
import com.pigs.holiday.repository.admin.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionService {

    final PermissionRepository permissionRepository;
    final PermissionMapper permissionMapper;

    // Toggle
    public PermissionDto.ToggleResDto toggle(PermissionDto.ToggleSevDto toggleSevDto){
        Permission permission = permissionRepository.findByRoleIdAndPermissionAndFunc(toggleSevDto.getRoleId(), toggleSevDto.getPermission(), toggleSevDto.getFunc()).orElse(null);

        if(permission == null){
            if(toggleSevDto.getActive()){
                return permissionRepository.save(toggleSevDto.toEntity()).toToggleResDto();
            }
        }else{
            permission.setDeleted(!toggleSevDto.getActive());
            return permissionRepository.save(permission).toToggleResDto();
        }

        return PermissionDto.ToggleResDto.builder().id((long)-100).build();
    }

    // List
    public List<PermissionDto.ListResDto> list(PermissionDto.ListSevDto listSevDto){
        List<PermissionDto.ListResDto> res = permissionMapper.list(listSevDto);

        return res;
    }
}
