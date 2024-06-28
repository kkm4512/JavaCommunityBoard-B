package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardServiceInterface {
    /**
     * CRUD
     */

    /**
     2-1-1. saveBoard
     2-1-1-1. 제목,본문,닉네임 모두 중복 될 수 있음
     2-1-1-2. DTO,Entity 도매인 객체 생성시 데이터의 무결성만 확인 //유효한 데이터 타입인지
     */
    public boolean saveBoard(BoardDTO boardDTO);

    /**
     2-1-2. getBoardById
     2-1-2-1. boardId 로 board 뽑아주기
     */
    public BoardEntity getBoardById(BoardDTO boardDTO);

    /**
     2-1-3. updateBoard
     2-1-3-1. boardId 로 찾고, update 해주기
     */
    public boolean updateBoard(BoardDTO boardDTO);

    /**
     2-1-4. removeBoard
     2-1-4-1. boardId 로 찾고, remove
     */
    public boolean removeBoard(Long boardId);

    /**
     2-1-5. getAllBoards
     2-1-5-1. 전체 board 반환
     */
    public List<BoardEntity> getAllBoards();

    /**
     * 프론트한테 boardId 받고, 해당 id의 updatedAt return
     */
    public LocalDateTime getUpdatedAt(Long id);

}
