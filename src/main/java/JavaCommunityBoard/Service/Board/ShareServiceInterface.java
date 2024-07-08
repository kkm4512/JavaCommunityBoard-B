package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.ShareDTO;

import java.util.List;

public interface ShareServiceInterface {

    List<BoardDTO> getSharedBoards(Long loginMemberId);
    boolean removeSharedBoard(ShareDTO shareDTO);
}
