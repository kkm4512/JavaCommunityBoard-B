package JavaCommunityBoard.Controller.inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquirySaveDTO;
import JavaCommunityBoard.Service.Inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;

    @SneakyThrows
    @PostMapping(value = "/save", consumes = "multipart/form-data")
    public Long saveInquiry(@RequestPart(value = "inquirySaveDTO") InquirySaveDTO inquirySaveDTO, @RequestPart(value = "saveInquiryImage", required = false) MultipartFile saveInquiryImage){
        return inquiryService.saveInquiry(inquirySaveDTO,saveInquiryImage);
    }

    @GetMapping("/getAll")
    public List<InquiryDTO> getAllInquiries(){
        return inquiryService.getAllInquires();
    }

    @DeleteMapping("/delete/{inquiryId}")
    public boolean updateInquiry(@PathVariable("inquiryId") Long inquiryId ){
        return inquiryService.deleteInquiry(inquiryId);
    }




}
