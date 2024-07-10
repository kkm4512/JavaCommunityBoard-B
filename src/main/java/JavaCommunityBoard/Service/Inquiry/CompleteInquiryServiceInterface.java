package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;

import java.util.List;

public interface CompleteInquiryServiceInterface {
    boolean saveCompleteInquiry(Long inquiryId);
    List<CompleteInquiryDTO> getAllByMemberId(Long memberId);
    boolean updateStatusCompleteInquiry(Long inquiryId);
    boolean updateStatusRejectedInquiry(Long inquiryId);
    Long deleteCompleteInquiry(Long inquiryId);
    boolean deleteCompleteInquiries(Long[] inquiryIds);
}
