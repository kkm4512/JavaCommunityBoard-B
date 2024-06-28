package JavaCommunityBoard.Controller.Board;


import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.OnUpdate;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Service.Board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save")
    public boolean saveBoard(@Valid @RequestBody BoardDTO boardDTO){
        return boardService.saveBoard(boardDTO);
    }

    @GetMapping("/getBoards")
    public List<BoardEntity> getBoards(){
        return boardService.getAllBoards();
    }

    @PutMapping("/updateBoard")
    public boolean updateBoard(@Validated(OnUpdate.class) @RequestBody BoardDTO boardDTO){
        return boardService.updateBoard(boardDTO);
    }

    @DeleteMapping("/removeBoard")
    public boolean removeBoard(@RequestBody Long boardId){
        return boardService.removeBoard(boardId);
    }

    @PostMapping("/getBoardUpdatedAt")
    public LocalDateTime getUpdatedAt(@RequestBody Long boardId){
        return boardService.getUpdatedAt(boardId);
    }

}
