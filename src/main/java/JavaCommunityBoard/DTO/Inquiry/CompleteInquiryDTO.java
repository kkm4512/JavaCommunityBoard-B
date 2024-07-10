package JavaCommunityBoard.DTO.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompleteInquiryDTO {

    private Long id;
    private String title;
    private String description;
    private InquiryCategory category;
    private Long memberId;
    private LocalDateTime createdAt;
    private String filePath;
    private Long inquiryId;
    private CompleteInquiryCompleted status;
    private RejectInquiryDTO rejectInquiryDTO;


    @Override
    public String toString() {
        return "CompleteInquiryDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", memberId=" + memberId +
                ", createdAt=" + createdAt +
                ", filePath='" + filePath + '\'' +
                ", inquiryId=" + inquiryId +
                ", completed=" + status +
                '}';
    }
}
