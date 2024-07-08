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
    private Long memberId;
    private InquiryDTO inquiryDTO;
    private LocalDateTime createdAt;

}
