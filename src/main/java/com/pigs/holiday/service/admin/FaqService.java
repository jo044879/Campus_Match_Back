package com.pigs.holiday.service.admin;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.admin.Faq;
import com.pigs.holiday.dto.admin.FaqDto;
import com.pigs.holiday.dto.admin.RoleUserDto;
import com.pigs.holiday.mapper.admin.FaqMapper;
import com.pigs.holiday.repository.admin.FaqRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FaqService {

    final String permission = "faq";
    final RoleUserService roleUserService;

    final FaqRepository faqRepository;
    final FaqMapper faqMapper;

    // Create
    public FaqDto.CreateResDto create(FaqDto.CreateSevDto createSevDto) {
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(createSevDto.getReqUserId()).permission(permission).func(120).build());

        FaqDto.CreateResDto res = faqRepository.save(createSevDto.toEntity()).toCreateResDto();

        return res;
    }

    // Detail
    public FaqDto.DetailResDto detail(FaqDto.DetailSevDto detailSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(detailSevDto.getReqUserId()).permission(permission).func(150).build());

        FaqDto.DetailResDto res = faqMapper.detail(detailSevDto);

        return res;
    }

    // List
    public List<FaqDto.ListResDto> list(FaqDto.ListSevDto listSevDto){

        List<FaqDto.ListResDto> res = faqMapper.list(listSevDto);

        return res;
    }

    // Update
    public void update(FaqDto.UpdateSevDto updateSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(updateSevDto.getReqUserId()).permission(permission).func(180).build());

        Faq faq = faqRepository.findById(updateSevDto.getId()).orElse(null);
        if(faq == null){
            throw new RuntimeException("no data");
        }

        if(updateSevDto.getTitle() != null){
            faq.setTitle(updateSevDto.getTitle());
        }
        if(updateSevDto.getContent() != null){
            faq.setContent(updateSevDto.getContent());
        }

        faqRepository.save(faq);
    }

    // Delete
    public void delete(FaqDto.DeleteSevDto deleteSevDto){
        roleUserService.permit(RoleUserDto.PermitSevDto.builder().reqUserId(deleteSevDto.getReqUserId()).permission(permission).func(200).build());

        Faq faq = faqRepository.findById(deleteSevDto.getId()).orElse(null);
        if(faq == null){
            throw new RuntimeException("no data");
        }

        faq.setDeleted(true);

        faqRepository.save(faq);
    }
}
