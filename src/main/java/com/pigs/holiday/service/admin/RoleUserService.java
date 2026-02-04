package com.pigs.holiday.service.admin;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.admin.RoleUser;
import com.pigs.holiday.dto.admin.RoleUserDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.mapper.admin.RoleUserMapper;
import com.pigs.holiday.repository.admin.RoleUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleUserService {

    final RoleUserRepository roleUserRepository;
    final RoleUserMapper roleUserMapper;

    // Permit
    public void permit(RoleUserDto.PermitSevDto permitSevDto){
        if(roleUserMapper.permit(permitSevDto) == 0){
            throw new NoPermissionException("No Auth");
        }
    }

    // Add
    public void add(RoleUserDto.AddUserSevDto addUserSevDto) {

        if (addUserSevDto.getAddSevDtoList() != null) {
            for (RoleUserDto.AddSevDto each : addUserSevDto.getAddSevDtoList()) {
                each.setReqUserId(addUserSevDto.getReqUserId());

                RoleUser roleUser = roleUserRepository.findByRoleIdAndUserId(each.getRoleId(), each.getUserId()).orElse(null);

                if(roleUser == null) {
                    roleUser = each.toEntity();
                }else{
                    roleUser.setDeleted(false);
                }

                roleUserRepository.save(roleUser).toAddResDto();
            }
        }
    }

    // UserList
    public List<RoleUserDto.ListResDto> userList(RoleUserDto.ListSevDto listSevDto){
        List<RoleUserDto.ListResDto> res = roleUserMapper.userList(listSevDto);

        return res;
    }

    // AddUserList
    public List<RoleUserDto.AddListResDto> addUserList(RoleUserDto.AddListSevDto addListSevDto){

        addListSevDto.setDeleted(true);

        List<RoleUserDto.AddListResDto> res = roleUserMapper.addUserList(addListSevDto);

        return res;
    }

    // RoleList
    public List<RoleUserDto.ListResDto> roleList(RoleUserDto.ListSevDto listSevDto){
        List<RoleUserDto.ListResDto> res = roleUserMapper.roleList(listSevDto);

        return res;
    }

    // AddRoleList
    public List<RoleUserDto.AddListResDto> addRoleList(RoleUserDto.AddListSevDto addListSevDto){

        addListSevDto.setDeleted(true);

        List<RoleUserDto.AddListResDto> res = roleUserMapper.addRoleList(addListSevDto);

        return res;
    }

    public void delete(RoleUserDto.DeleteSevDto deleteSevDto){
        RoleUser roleUser = roleUserRepository.findByRoleIdAndUserId(deleteSevDto.getRoleId(), deleteSevDto.getUserId()).orElse(null);

        if(roleUser == null){
            throw new RuntimeException("no data");
        }

        roleUser.setDeleted(true);

        roleUserRepository.save(roleUser);
    }

}
