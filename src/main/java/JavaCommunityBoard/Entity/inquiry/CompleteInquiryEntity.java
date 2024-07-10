package JavaCommunityBoard.Entity.inquiry;

import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryCompleted;
import JavaCommunityBoard.DTO.Inquiry.InquiryCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "completeInquiries")
public class CompleteInquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String title;
    private String description;
    private InquiryCategory category;
    private String filePath;
    private Long inquiryId;
    private CompleteInquiryCompleted status;

    @OneToOne(mappedBy = "completeInquiryEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private RejectInquirySaveEntity rejectInquirySaveEntity;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "CompleteInquiryEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", filePath='" + filePath + '\'' +
                ", inquiryId=" + inquiryId +
                ", completed=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
