package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.BoardValidated.OnSave;
import JavaCommunityBoard.DTO.Board.BoardValidated.OnUpdate;
import JavaCommunityBoard.Paths.PathConstants;
import JavaCommunityBoard.Service.Board.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    @SneakyThrows
    @PostMapping(value = "/save", consumes = "multipart/form-data")
    public boolean saveBoard(@Validated(OnSave.class) @RequestPart(value = "boardInfo") BoardDTO boardDTO, @RequestPart(value = "boardImage", required = false) MultipartFile boardImage ){
        return boardService.saveBoard(boardDTO,boardImage, PathConstants.GET_BOARDS_FILE_BASEURL);
    }

    @GetMapping("/getBoards")
    public List<BoardDTO> getBoards(){
        return boardService.getAllBoards();
    }

    @SneakyThrows
    @PutMapping("/updateBoard")
    public boolean updateBoard(@Validated(OnUpdate.class) @RequestPart(value = "updateBoard") BoardDTO boardDTO, @RequestPart(value = "updateBoardImage", required = false) MultipartFile updateBoardImage){
        return boardService.updateBoard(boardDTO,updateBoardImage,PathConstants.GET_BOARDS_FILE_BASEURL);
    }

    @DeleteMapping("/removeBoard")
    public boolean removeBoard(@RequestBody Long boardId){
        return boardService.removeBoard(boardId);
    }

    @PostMapping("/getBoardUpdatedAt")
    public LocalDateTime getUpdatedAt(@RequestBody Long boardId){
        return boardService.getUpdatedAt(boardId);
    }

    @GetMapping("/getBoardImage/{boardId}")
    public String getBoardImage(@PathVariable("boardId") long boardId){
        return boardService.loadBoardImage(boardId,PathConstants.GET_BOARDS_FILE_BASEURL);
    }

    @GetMapping("/getBoards/{memberId}")
    public List<BoardDTO> getBoardsById(@PathVariable("memberId") Long memberId){
        return boardService.getAllBoardsById(memberId);
    }

}
