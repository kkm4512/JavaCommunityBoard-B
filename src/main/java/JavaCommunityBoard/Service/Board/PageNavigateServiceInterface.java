package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Inquiry.CompleteInquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PageNavigateServiceInterface {
    List<BoardDTO> getPageSendBoards(int pageNumber);
    List<BoardDTO> getLoginMemberIdAndPageSendBoards(int pageNumber, Long loginMemberId);
    List<BoardDTO> getLoginMemberIdAndPageSendShareBoards(int pageNumber, Long loginMemberId);
    List<BoardDTO> getAllShareBoardsAndBoards(int pageNumber, Long loginMemberId);
    List<BoardDTO> getPageAllBoardsById(Long loginMemberId);
    List<BoardDTO> getPageAllShareBoardsById(Long loginMemberId);
    List<CompleteInquiryDTO> getAllInquiries(int PageNumber);
    List<CompleteInquiryDTO> getAllInquiryCompletes(int pageNumber, Long memberId);
}
