package JavaCommunityBoard.DTO.Board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoardDTO {

    //게시글 id
    @NotNull(message = "[DTO] boardId는 공백 일 수 없습니다", groups = {OnUpdate.class, OnRemove.class})
    private Long boardId;

    //멤버 id
    @NotNull(message = "[DTO] memberId는 공백 일 수 없습니다")
    private Long memberId;

    @NotBlank(message = "[DTO] 제목은 공백 일 수 없습니다", groups = {OnUpdate.class})
    @Size(min = 1,max = 20,message = "[DTO] 제목은 최소 1글자, 최대 20글자 이하입니다")
    private String title;

    @NotBlank(message = "[DTO] 본문은 공백 일 수 없습니다", groups = {OnUpdate.class})
    @Size(min = 1,max = 255,message = "[DTO] 본문은 최소 1글자, 최대 255글자 이하입니다")
    private String description;

    @NotBlank(message = "[DTO] 닉네임은 공백 일 수 없습니다", groups = {OnUpdate.class})
    @Size(min = 1,max = 10,message = "[DTO] 닉네임은 최소 1글자, 최대 10글자 이하입니다")
    private String nickname;

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardId=" + boardId +
                ", memberId=" + memberId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
