package JavaCommunityBoard.Controller.inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;
import JavaCommunityBoard.Service.Inquiry.CompleteInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/completeInquiry")
public class CompleteInquiryController {
    private final CompleteInquiryService completeInquiryService;

    @GetMapping("/save/{inquiryId}")
    public boolean saveCompleteInquiry(@PathVariable("inquiryId") Long inquiryId){
        return completeInquiryService.saveCompleteInquiry(inquiryId);
    }

    @GetMapping("/getAll/{memberId}")
    public List<CompleteInquiryDTO> getAllByMemberId(@PathVariable("memberId") Long memberId){
        return completeInquiryService.getAllByMemberId(memberId);
    }

    @PutMapping("/update/complete/{inquiryId}")
    public boolean updateStatusCompleteInquiry(@PathVariable("inquiryId")Long inquiryId){
        return completeInquiryService.updateStatusCompleteInquiry(inquiryId);
    }

    @PutMapping("/update/reject/{inquiryId}")
    public boolean updateStatusRejectedInquiry(@PathVariable("inquiryId")Long inquiryId){
        return completeInquiryService.updateStatusRejectedInquiry(inquiryId);
    }

    @DeleteMapping("/delete/{inquiryId}")
    public Long deleteCompleteInquiry(@PathVariable("inquiryId")Long inquiryId){
        return completeInquiryService.deleteCompleteInquiry(inquiryId);
    }

    @DeleteMapping("/deletes")
    public boolean deleteCompleteInquiries(@RequestBody Long[] inquiryIds) {
        return completeInquiryService.deleteCompleteInquiries(inquiryIds);
    }
}
