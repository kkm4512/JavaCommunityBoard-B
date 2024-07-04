package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.CommentDTO;
import JavaCommunityBoard.DTO.Board.CommentValidated.OnSaveComment;
import JavaCommunityBoard.Service.Board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/board")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public boolean getByMemberIdAndBoardIdSetComment(@Validated(OnSaveComment.class) @RequestBody CommentDTO commentDTO){
        return commentService.setComments(commentDTO);
    }

    @PutMapping("/comment/update")
    public void commentUpdate(@RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/comment/remove")
    public void commentRemove(@RequestBody CommentDTO commentDTO){
        commentService.removeComment(commentDTO);
    }

}
