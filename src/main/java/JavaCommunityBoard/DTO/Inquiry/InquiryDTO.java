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
public class InquiryDTO {

    private Long id;
    private String title;
    private String description;
    private InquiryCategory category;
    private Long memberId;
    private LocalDateTime createdAt;
    private String filePath;
}
