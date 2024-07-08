package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquirySaveDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InquiryServiceInterface {
    void saveInquiry(InquirySaveDTO inquirySaveDTO, MultipartFile saveInquiryImage);
    List<InquiryDTO> getAllInquires();
    boolean deleteInquiry(Long id);
}
