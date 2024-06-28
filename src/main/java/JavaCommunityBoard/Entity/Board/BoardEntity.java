package JavaCommunityBoard.Entity.Board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "board")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private Long memberId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String nickname;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    BoardEntity() {}

    //updateBoard 데이터 확인
    //saveBoard 데이터 확인
    @Builder
    public BoardEntity(Long memberId, Long boardId, String title, String description, String nickname){
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.description = description;
        this.nickname = nickname;

        if (memberId == null)
            updateBoardValidate();
        else
            saveBoardValidate();
        }


    //saveBoard 데이터 확인
    private void saveBoardValidate() {
        //null 확인
        if (this.memberId == null) throw new IllegalStateException("[Entity] memberId는 공백 일 수 없습니다");
        if (this.title.isEmpty()) throw new IllegalStateException("[Entity] 제목은 공백 일 수 없습니다");
        if (this.nickname.isEmpty()) throw new IllegalStateException("[Entity] 닉네임은 공백 일 수 없습니다");
        if (this.description.isEmpty()) throw new IllegalStateException("[Entity] 본문은 공백 일 수 없습니다");

        //글자수 확인
        if (this.title.length() > 20) throw new IllegalStateException("[Entity] 제목은 최소 1글자, 최대 20글자 이하입니다");
        if (this.nickname.length() > 10) throw new IllegalStateException("[Entity] 닉네임은 최소 1글자, 최대 10글자 이하입니다");
        if (this.description.length() > 255) throw new IllegalStateException("[Entity] 본문은 최소 1글자, 최대 255글자 이하입니다");
    }

    //updateBoard 데이터 확인
    private void updateBoardValidate() {
        //null 확인
        if (this.boardId == null) throw new IllegalStateException("[Entity] boardId는 공백 일 수 없습니다");
        if (this.title.isEmpty()) throw new IllegalStateException("[Entity] 제목은 공백 일 수 없습니다");
        if (this.description.isEmpty()) throw new IllegalStateException("[Entity] 본문은 공백 일 수 없습니다");
        if (this.nickname.isEmpty()) throw new IllegalStateException("[Entity] 닉네임은 공백 일 수 없습니다");

        //글자수 확인
        if (this.title.length() > 20) throw new IllegalStateException("[Entity] 제목은 최소 1글자, 최대 20글자 이하입니다");
        if (this.description.length() > 255) throw new IllegalStateException("[Entity] 본문은 최소 1글자, 최대 255글자 이하입니다");
        if (this.nickname.length() > 10) throw new IllegalStateException("[Entity] 닉네임은 최소 1글자, 최대 10글자 이하입니다");
    }
}
