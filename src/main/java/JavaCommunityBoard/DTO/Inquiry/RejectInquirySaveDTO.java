package JavaCommunityBoard.DTO.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RejectInquirySaveDTO {
    private CompleteInquiryDTO completeInquiry;
    private RejectInquiryDTO rejectInquiry;

    @Override
    public String toString() {
        return "RejectInquirySaveDTO{" +
                "completeInquiryDTO=" + completeInquiry +
                ", rejectInquiryDTO=" + rejectInquiry +
                '}';
    }
}
