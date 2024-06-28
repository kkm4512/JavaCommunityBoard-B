package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceInterface{

    private final BoardRepository boardRepository;

    @Override
    public boolean saveBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity(boardDTO.getMemberId(),boardDTO.getBoardId(),boardDTO.getTitle(),boardDTO.getDescription(),boardDTO.getNickname());
        boardRepository.save(boardEntity);
        return true;
    }

    @Override
    public BoardEntity getBoardById(BoardDTO boardDTO) {
        return null;
    }

    @Override
    public boolean updateBoard(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getBoardId()).orElseThrow(()-> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다"));
        BoardEntity saveBoardEntity = new BoardEntity(boardDTO.getMemberId(),boardDTO.getBoardId(),boardDTO.getTitle(),boardDTO.getDescription(),boardDTO.getNickname());
        boardRepository.save(saveBoardEntity);
        return true;
    }

    @Override
    public boolean removeBoard(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(()-> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다"));
        boardRepository.delete(boardEntity);
        return true;
    }

    @Override
    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public LocalDateTime getUpdatedAt(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow( () -> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다") ).getUpdatedAt();

    }
}
