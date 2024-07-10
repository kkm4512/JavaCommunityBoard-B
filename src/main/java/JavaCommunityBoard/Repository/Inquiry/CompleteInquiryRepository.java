package JavaCommunityBoard.Repository.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryCompleted;
import JavaCommunityBoard.Entity.inquiry.CompleteInquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompleteInquiryRepository extends JpaRepository<CompleteInquiryEntity,Long> {
    List<CompleteInquiryEntity> findAllByMemberId(Long memberId);
    Optional<CompleteInquiryEntity> findByInquiryId(Long inquiryId);
    Page<CompleteInquiryEntity> findAllByMemberId(Long memberId, Pageable pageable);
    List<CompleteInquiryEntity> findAllByInquiryIdIn(List<Long> inquiryIds);
    Page<CompleteInquiryEntity> findAllByStatusOrderByCreatedAtAsc(CompleteInquiryCompleted status, Pageable pageable);
}
