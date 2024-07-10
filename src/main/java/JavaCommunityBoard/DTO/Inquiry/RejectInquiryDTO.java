package JavaCommunityBoard.DTO.Inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RejectInquiryDTO {
    String title;
    String description;

    @Override
    public String toString() {
        return "RejectInquiryDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
