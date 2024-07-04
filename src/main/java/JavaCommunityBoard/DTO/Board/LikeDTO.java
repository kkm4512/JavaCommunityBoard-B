package JavaCommunityBoard.DTO.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {

    private Long id;
    private Long memberId;
    private Long boardId;
    private Boolean liked;
}
