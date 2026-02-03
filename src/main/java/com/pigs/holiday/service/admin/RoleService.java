package com.pigs.holiday.service.admin;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.admin.Role;
import com.pigs.holiday.dto.admin.PermissionDto;
import com.pigs.holiday.dto.admin.RoleDto;
import com.pigs.holiday.dto.admin.RoleUserDto;
import com.pigs.holiday.mapper.admin.RoleMapper;
import com.pigs.holiday.repository.admin.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {

    final String permission = "role";

    final RoleRepository roleRepository;
    final RoleMapper roleMapper;
    final PermissionService permissionService;
    final RoleUserService roleUserService;


    // Create
    public RoleDto.CreateResDto create(RoleDto.CreateSevDto createSevDto) {
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        RoleDto.CreateResDto res = roleRepository.save(createSevDto.toEntity()).toCreateResDto();

        return res;
    }

    // Detail
    public RoleDto.DetailResDto detail(RoleDto.DetailSevDto detailSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        RoleDto.DetailResDto res = roleMapper.detail(detailSevDto);

        res.setPermissionList(permissionService.list(PermissionDto.ListSevDto.builder().deleted(false).roleId(detailSevDto.getId()).reqUserId(detailSevDto.getReqUserId()).build()));
        res.setRoleUserList(roleUserService.userList(RoleUserDto.ListSevDto.builder().deleted(false).reqId(detailSevDto.getId()).reqUserId(detailSevDto.getReqUserId()).build()));
        res.setPermissions(RoleDto.permissions);

        return res;
    }

    // List
    public List<RoleDto.ListResDto> list(RoleDto.ListSevDto listSevDto){
        List<RoleDto.ListResDto> res = roleMapper.list(listSevDto);

        return res;
    }

    // Update
    public void update(RoleDto.UpdateSevDto updateSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        Role role = roleRepository.findById(updateSevDto.getId()).orElse(null);
        if(role == null){
            throw new RuntimeException("no data");
        }

        if(updateSevDto.getRoleName() != null){
            role.setRoleName(updateSevDto.getRoleName());
        }
        if(updateSevDto.getContent() != null){
            role.setContent(updateSevDto.getContent());
        }

        if (updateSevDto.getPermissionUpdate() != null) {
            for (PermissionDto.ToggleSevDto each : updateSevDto.getPermissionUpdate()) {
                each.setRoleId(role.getId());
                permissionService.toggle(each);
            }
        }

        roleRepository.save(role);
    }

    // Delete
    public void delete(RoleDto.DeleteSevDto deleteSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        Role role = roleRepository.findById(deleteSevDto.getId()).orElse(null);
        if(role == null){
            throw new RuntimeException("no data");
        }

        role.setDeleted(true);

        roleRepository.save(role);
    }
}
