package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.ShareDTO;
import JavaCommunityBoard.Service.Board.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
public class ShareController {

    private final ShareService shareService;

    @PostMapping("board/share")
    public boolean createShareBoard (@RequestBody ShareDTO shareDTO){
        return shareService.createShareBoard(shareDTO);
    }

//    @GetMapping("board/getShareBoards/{loginMemberId}")
//    public List<ShareDTO> getShareBoards(@PathVariable("loginMemberId") Long loginMemberId){
//        return shareService.getShareBoards(loginMemberId);
//    }
//
//    @DeleteMapping("board/share/removeBoard")
//    public boolean removeBoard(@RequestBody ShareDTO shareDTO){
//        return shareService.removeBoard(shareDTO);
//    }
}
