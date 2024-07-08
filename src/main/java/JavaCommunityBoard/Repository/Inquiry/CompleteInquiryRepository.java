package JavaCommunityBoard.Repository.Inquiry;

import JavaCommunityBoard.Entity.inquiry.CompleteInquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteInquiryRepository extends JpaRepository<CompleteInquiryEntity,Long> { }
