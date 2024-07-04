package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PageNavigateServiceInterface {
    List<BoardDTO> getPageSendBoards(int pageNumber);
}
