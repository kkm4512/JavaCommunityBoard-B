package JavaCommunityBoard.Controller.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.Service.Board.PageNavigateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/pageNavigate")
@CrossOrigin
@RequiredArgsConstructor
public class PageNavigateController {

    private final PageNavigateService pageNavigateService;

    //전체 게시글에서 몇번째 페이지의 게시물 건내줄때
    @GetMapping("/{pageNumber}")
    public List<BoardDTO> getPageSendBoards(@PathVariable("pageNumber") int pageNumber ){
        return pageNavigateService.getPageSendBoards(pageNumber);
    }

    //전체 게시글에서 몇번째 페이지,자신의 게시물들을 건내줄때
    @GetMapping("/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getLoginMemberIdAndPageSendBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getLoginMemberIdAndPageSendBoards(pageNumber,loginMemberId);
    }

    //전체 게시글에서 몇번째 페이지,자신이 공유한 게시물을 건내줄때
    @GetMapping("/share/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getLoginMemberIdAndPageSendShareBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getLoginMemberIdAndPageSendShareBoards(pageNumber,loginMemberId);
    }

    // getLoginMemberIdAndPageSendBoards + getLoginMemberIdAndPageSendShareBoards
    @GetMapping("/allBoards/{pageNumber}/{loginMemberId}")
    public List<BoardDTO> getAllShareBoardsAndBoards(@PathVariable("pageNumber") int pageNumber, @PathVariable("loginMemberId") Long loginMemberId){
        return pageNavigateService.getAllShareBoardsAndBoards(pageNumber,loginMemberId);
    }

    //문의받은 게시글에서 몇번째 페이지의 게시글을 건내줄때
    @GetMapping("/getAllInquires/{pageNumber}")
    public List<CompleteInquiryDTO> getAllInquiries(@PathVariable("pageNumber") int pageNumber){
        return pageNavigateService.getAllInquiries(pageNumber);
    }

    @GetMapping("inquiryComplete/{pageNumber}/{memberId}")
    public List<CompleteInquiryDTO> getAllInquiryCompletes(@PathVariable("pageNumber") int pageNumber, @PathVariable("memberId") Long memberId){
        return pageNavigateService.getAllInquiryCompletes(pageNumber,memberId);
    }
}

