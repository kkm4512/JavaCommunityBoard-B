package JavaCommunityBoard.Controller.inquiry;

import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.Service.Inquiry.CompleteInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

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
}
