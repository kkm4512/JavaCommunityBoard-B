package JavaCommunityBoard.DTO.Board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long memberId;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotBlank(message = "댓글은 공란 일수 없습니다")
    private String comment;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", boardId=" + boardId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
