package JavaCommunityBoard.DTO.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareDTO {
    private Long sharedId;
    private Long boardId;
    private boolean shared;
    private Long loginMemberId;

    @Override
    public String toString() {
        return "ShareDTO{" +
                "sharedId=" + sharedId +
                ", boardId=" + boardId +
                ", shared=" + shared +
                ", loginMemberId=" + loginMemberId +
                '}';
    }
}
