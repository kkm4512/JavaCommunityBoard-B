package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.RejectInquirySaveDTO;
import JavaCommunityBoard.Entity.inquiry.CompleteInquiryEntity;
import JavaCommunityBoard.Entity.inquiry.RejectInquirySaveEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Inquiry.CompleteInquiryRepository;
import JavaCommunityBoard.Repository.Inquiry.RejectInquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectInquirySaveService implements RejectInquirySaveInterface{
    private final CompleteInquiryRepository completeInquiryRepository;
    private final RejectInquiryRepository rejectInquiryRepository;

    @Transactional
    @Override
    public boolean saveRejectInquiry(RejectInquirySaveDTO rejectInquiry) {
        CompleteInquiryEntity completeInquiryEntity = completeInquiryRepository.findByInquiryId(rejectInquiry
                .getCompleteInquiry().getInquiryId()).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 게시글은 정상적이지 않습니다"));

        RejectInquirySaveEntity rejectInquirySaveEntity = new RejectInquirySaveEntity();

        rejectInquirySaveEntity.setCompleteInquiryEntity(completeInquiryEntity);

        rejectInquirySaveEntity.setTitle(rejectInquiry.getRejectInquiry().getTitle());
        rejectInquirySaveEntity.setDescription(rejectInquiry.getRejectInquiry().getDescription());


        rejectInquiryRepository.save(rejectInquirySaveEntity);
        return true;
    }
}
