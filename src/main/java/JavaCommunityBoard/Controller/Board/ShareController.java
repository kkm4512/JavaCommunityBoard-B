package JavaCommunityBoard.Controller.Board;


import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.ShareDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.ShareEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.ShareRepository;
import JavaCommunityBoard.Service.Board.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@RequiredArgsConstructor
public class ShareController {

    private final ShareRepository shareRepository;
    private final ShareService shareService;
    private final BoardRepository boardRepository;

    @PostMapping("/share")
    public boolean createShare(@RequestBody ShareDTO shareDTO){
        BoardEntity boardEntity = boardRepository.findById(shareDTO.getBoardId()).orElseThrow(() -> new HandleMisMatchBoardInfo("Not Found Your Board"));
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setBoardEntity(boardEntity);
        shareEntity.setLoginMemberId(shareDTO.getLoginMemberId());
        shareRepository.save(shareEntity);
        return true;
    }

    @GetMapping("/getShareBoards/{loginMyId}")
    public List<BoardDTO> getSharedBoards(@PathVariable Long loginMyId) {
        return shareService.getSharedBoards(loginMyId);
    }

    @DeleteMapping("/share/removeBoard")
    public boolean removeShareBoard(@RequestBody ShareDTO shareDTO){
        return shareService.removeSharedBoard(shareDTO);
    }
}
