package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryCompleted;
import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;
import JavaCommunityBoard.Entity.inquiry.CompleteInquiryEntity;
import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Inquiry.CompleteInquiryRepository;
import JavaCommunityBoard.Repository.Inquiry.InquiryRepository;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompleteInquiryService implements CompleteInquiryServiceInterface{

    @Autowired
    private Convert convert;

    @Autowired
    private final CompleteInquiryRepository completeInquiryRepository;

    @Autowired
    private final InquiryRepository inquiryRepository;

    @Transactional
    @Override
    public boolean saveCompleteInquiry(Long inquiryId) {
        InquiryEntity inquiryEntity = inquiryRepository.findById(inquiryId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 게시글은 유효하지 않습니다"));
        CompleteInquiryEntity completeInquiryEntity = new CompleteInquiryEntity();
        completeInquiryEntity.setTitle(inquiryEntity.getTitle());
        completeInquiryEntity.setCategory(inquiryEntity.getCategory());
        completeInquiryEntity.setDescription(inquiryEntity.getDescription());
        completeInquiryEntity.setFilePath(inquiryEntity.getFilePath());
        completeInquiryEntity.setMemberId(inquiryEntity.getMemberId());
        completeInquiryEntity.setInquiryId(inquiryEntity.getId());
        completeInquiryEntity.setStatus(CompleteInquiryCompleted.IN_PROCESS);
        completeInquiryRepository.save(completeInquiryEntity);
        return true;
    }

    @Transactional
    @Override
    public List<CompleteInquiryDTO> getAllByMemberId(Long memberId) {
        List<CompleteInquiryEntity> allByMemberId = completeInquiryRepository.findAllByMemberId(memberId);
        return allByMemberId.stream()
                .map(convert::completeInquiryEntityToDTO)
                .toList();

    }

    @Transactional
    @Override
    public boolean updateStatusCompleteInquiry(Long inquiryId) {
        CompleteInquiryEntity completeInquiryEntity = completeInquiryRepository.findByInquiryId(inquiryId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 업데이트글을 찾을 수 없습니다"));
        completeInquiryEntity.setStatus(CompleteInquiryCompleted.FULFILLED);
        completeInquiryRepository.save(completeInquiryEntity);
        return true;
    }

    @Transactional
    @Override
    public boolean updateStatusRejectedInquiry(Long inquiryId) {
        CompleteInquiryEntity completeInquiryEntity = completeInquiryRepository.findByInquiryId(inquiryId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 업데이트글을 찾을 수 없습니다"));
        completeInquiryEntity.setStatus(CompleteInquiryCompleted.REJECTED);
        completeInquiryRepository.save(completeInquiryEntity);
        return true;
    }

    @Transactional
    @Override
    public Long deleteCompleteInquiry(Long inquiryId) {
        CompleteInquiryEntity completeInquiryEntity = completeInquiryRepository.findByInquiryId(inquiryId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 게시글은 조회 되지 않습니다"));
        completeInquiryRepository.delete(completeInquiryEntity);
        return inquiryId;
    }

    @Transactional
    @Override
    public boolean deleteCompleteInquiries(Long[] inquiryIds) {
        List<CompleteInquiryEntity> completeInquiryEntities = completeInquiryRepository.findAllByInquiryIdIn(Arrays.asList(inquiryIds));
        completeInquiryRepository.deleteAll(completeInquiryEntities);
        return true;
    }
}
