package JavaCommunityBoard.Entity.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.CommentDTO;
import JavaCommunityBoard.DTO.Board.LikeDTO;
import JavaCommunityBoard.Entity.MemberEntity;
import jakarta.persistence.*;
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
@Entity
public class ShareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean shared;

    @Column
    private String writerImagePath;

    @Column
    private String boardImagePath;

    @Column
    private Long loginMemberId;

    @Column
    private Long boardId;

    @Column
    private Long memberId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private String nickname;


    @Override
    public String toString() {
        return "ShareEntity{" +
                "id=" + id +
                ", shared=" + shared +
                ", writerImagePath='" + writerImagePath + '\'' +
                ", loginMemberId=" + loginMemberId +
                '}';
    }
}
