package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.Service.Board.PageNavigateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@RequiredArgsConstructor
public class PageNavigateController {

    private final PageNavigateService pageNavigateService;

    @GetMapping("/pageNavigate/{pageNumber}")
    public List<BoardDTO> getPageSendBoards(@PathVariable("pageNumber") int pageNumber ){
        return pageNavigateService.getPageSendBoards(pageNumber);
    }

//    @GetMapping("/pageNavigate/{page}/{loginMemberId}")
    // loginMemberId의 게시물을 shareEntity 에서찾은다음,
    // 사용자가 요청한 page 에 맞게 게시물 짤라서 넘겨주기
//    public List<BoardDTO> getPageSendShareBoards(@PathVariable("page") int page, @PathVariable("loginMemberId") Long loginMemberId){
//
//    }
}
