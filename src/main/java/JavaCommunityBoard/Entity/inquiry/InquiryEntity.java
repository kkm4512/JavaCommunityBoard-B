package JavaCommunityBoard.Entity.inquiry;

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
public class InquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String title;
    private String description;
    private InquiryCategory category;
    private String filePath;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inquiry_id")
    private CompleteInquiryEntity completeInquiryEntity;



    @Override
    public String toString() {
        return "InquiryEntity{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", filePath='" + filePath + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
