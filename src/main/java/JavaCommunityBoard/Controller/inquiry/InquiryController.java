package JavaCommunityBoard.Controller.inquiry;

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
    public void saveInquiry(@RequestPart(value = "inquirySaveDTO") InquirySaveDTO inquirySaveDTO, @RequestPart(value = "saveInquiryImage", required = false) MultipartFile saveInquiryImage){
        inquiryService.saveInquiry(inquirySaveDTO,saveInquiryImage);
    }

    @GetMapping("/getAll")
    public List<InquiryDTO> getAllInquiries(){
        return inquiryService.getAllInquires();
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteInquiry(@PathVariable("id") Long id ){
        return inquiryService.deleteInquiry(id);
    }
}
