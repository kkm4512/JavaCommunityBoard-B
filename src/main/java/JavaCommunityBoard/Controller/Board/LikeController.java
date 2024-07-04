package JavaCommunityBoard.Controller.Board;


import JavaCommunityBoard.Service.Board.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{memberId}/{boardId}")
    public void toggleLiked(@PathVariable("memberId") Long memberId, @PathVariable("boardId") Long boardId){
        likeService.toggleLike(memberId,boardId);
    }
}
