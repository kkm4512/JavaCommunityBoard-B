package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
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

    //전체 게시글에서 몇번째 페이지의 게시물 건내줄때
    @GetMapping("/pageNavigate/{pageNumber}")
    public List<BoardDTO> getPageSendBoards(@PathVariable("pageNumber") int pageNumber ){
        return pageNavigateService.getPageSendBoards(pageNumber);
    }

    //전체 게시글에서 몇번째 페이지,자신의 게시물들을 건내줄때
    @GetMapping("/pageNavigate/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getLoginMemberIdAndPageSendBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getLoginMemberIdAndPageSendBoards(pageNumber,loginMemberId);
    }

    //전체 게시글에서 몇번째 페이지,자신이 공유한 게시물을 건내줄때
    @GetMapping("/pageNavigate/share/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getLoginMemberIdAndPageSendShareBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getLoginMemberIdAndPageSendShareBoards(pageNumber,loginMemberId);
    }

    // getLoginMemberIdAndPageSendBoards + getLoginMemberIdAndPageSendShareBoards
    @GetMapping("/pageNavigate/allBoards/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getAllShareBoardsAndBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getAllShareBoardsAndBoards(pageNumber,loginMemberId);
    }

    //문의받은 게시글에서 몇번째 페이지의 게시글을 건내줄때
    @GetMapping("pageNavigate/getAllInquires/{pageNumber}")
    public List<InquiryDTO> getAllInquiries(@PathVariable("pageNumber") int pageNumber){
        return pageNavigateService.getAllInquiries(pageNumber);
    }
}

