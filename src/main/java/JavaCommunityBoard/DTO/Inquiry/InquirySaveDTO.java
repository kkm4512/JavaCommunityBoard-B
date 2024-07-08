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
public class InquirySaveDTO {
    private String title;
    private String description;
    private InquiryCategory category;
    private Long memberId;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "InquirySaveDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", memberId=" + memberId +
                '}';
    }
}
