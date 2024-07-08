package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.Entity.inquiry.CompleteInquiryEntity;
import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Inquiry.CompleteInquiryRepository;
import JavaCommunityBoard.Repository.Inquiry.InquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompleteInquiryService implements CompleteInquiryServiceInterface{
    private final CompleteInquiryRepository completeInquiryRepository;
    private final InquiryRepository inquiryRepository;

    @Transactional
    @Override
    public boolean saveCompleteInquiry(Long inquiryId) {
        InquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 게시글은 유효하지 않습니다"));
        CompleteInquiryEntity completeInquiryEntity = new CompleteInquiryEntity();
        completeInquiryEntity.setInquiryId(inquiryId);
        completeInquiryEntity.setInquiryEntity(inquiryEntity);
        completeInquiryRepository.save(completeInquiryEntity);
        return true;
    }
}
