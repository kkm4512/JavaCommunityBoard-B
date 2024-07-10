package JavaCommunityBoard.Controller.inquiry;

import JavaCommunityBoard.DTO.Inquiry.RejectInquirySaveDTO;
import JavaCommunityBoard.Service.Inquiry.RejectInquirySaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/rejectInquiry")
public class RejectInquirySaveController {
    private final RejectInquirySaveService rejectInquirySaveService;

    @PostMapping("/save")
    public boolean saveRejectInquiry(@RequestBody RejectInquirySaveDTO rejectInquiry) {
        return rejectInquirySaveService.saveRejectInquiry(rejectInquiry);
    }
}
