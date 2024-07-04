package JavaCommunityBoard.DTO.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareDTO {
    private Long id;
    private boolean shared;
    private String writerImagePath;
    private String boardImagePath;
    private Long loginMemberId;
    private Long boardId;
    private Long memberId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String nickname;

    @Override
    public String toString() {
        return "ShareDTO{" +
                "id=" + id +
                ", shared=" + shared +
                ", writerImagePath='" + writerImagePath + '\'' +
                ", boardImagePath='" + boardImagePath + '\'' +
                ", loginMemberId=" + loginMemberId +
                ", boardId=" + boardId +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
