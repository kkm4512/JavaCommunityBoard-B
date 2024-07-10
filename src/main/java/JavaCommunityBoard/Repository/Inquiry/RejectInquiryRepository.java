package JavaCommunityBoard.Repository.Inquiry;

import JavaCommunityBoard.Entity.inquiry.RejectInquirySaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectInquiryRepository extends JpaRepository<RejectInquirySaveEntity,Long> { }
