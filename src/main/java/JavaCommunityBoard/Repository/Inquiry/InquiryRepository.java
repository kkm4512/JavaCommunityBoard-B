package JavaCommunityBoard.Repository.Inquiry;

import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity,Long> {
    List<InquiryEntity> findAllByOrderByCreatedAtDesc();
    List<InquiryEntity> findAllByOrderByCreatedAtAsc();
    Page<InquiryEntity> findAllByOrderByCreatedAtAsc(Pageable pageable);
}
