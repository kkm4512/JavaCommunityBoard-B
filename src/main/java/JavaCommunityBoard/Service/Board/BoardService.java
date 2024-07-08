package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.LikeRepository;
import JavaCommunityBoard.Service.File.FileDownloadService;
import JavaCommunityBoard.Service.File.FileUploadService;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceInterface{

    private final BoardRepository boardRepository;
    private final FileDownloadService fileDownloadService;
    private final FileUploadService fileUploadService;
    private final LikeRepository likeRepository;
    private final Convert convert;

    @SneakyThrows
    @Override
    public boolean saveBoard(BoardDTO boardDTO, MultipartFile boardImage,String uploadDir) {
        BoardEntity boardEntity = new BoardEntity(boardDTO.getMemberId(),boardDTO.getBoardId(),boardDTO.getTitle(),boardDTO.getDescription(),boardDTO.getNickname());
        String boardImagePath = null;
        if (boardImage != null &&  !boardImage.isEmpty()) {
            boardImagePath = fileUploadService.storeFile(boardImage,uploadDir);
            boardEntity.setBoardImagePath(boardImagePath);
        }
        boardRepository.save(boardEntity);
        return true;
    }


    @Override
    public BoardEntity getBoardById(BoardDTO boardDTO) {
        return null;
    }

    @SneakyThrows
    @Override
    public boolean updateBoard(BoardDTO boardDTO,MultipartFile updateBoardImage,String uploadDir) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getBoardId()).orElseThrow(()-> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다"));
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setDescription(boardDTO.getDescription());
        String updateBoardImagePath = null;

        if (updateBoardImage != null && !updateBoardImage.isEmpty()) {
            updateBoardImagePath = fileUploadService.storeFile(updateBoardImage, uploadDir);
            boardEntity.setBoardImagePath(updateBoardImagePath);
        }
        boardRepository.save(boardEntity);
        return true;
    }

    @Override
    public boolean removeBoard(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(()-> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다"));
        boardRepository.delete(boardEntity);
        return true;
    }

    @Transactional
    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardEntity> boardEntities = boardRepository.findAllByOrderByCreatedAtDesc();
        return boardEntities.stream().map(convert::boardEntityToDTO).toList();
    }

    @Override
    public LocalDateTime getUpdatedAt(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow( () -> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다") ).getUpdatedAt();
    }

    @Override
    public String loadBoardImage(Long boardId, String uploadDir) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 게시글은 존재하지 않습니다"));
        if (boardEntity.getBoardImagePath() == null) {
            return "";
        }
        String fileName = boardEntity.getBoardImagePath();
        return fileDownloadService.loadFileAsResource(fileName,uploadDir);
    }

    @Transactional
    @Override
    public List<BoardDTO> getAllBoardsById(Long memberId) {
        List<BoardEntity> boardEntities = boardRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);
        return boardEntities.stream().map(convert::boardEntityToDTO).toList();
    }

    @Transactional
    @Override
    public BoardDTO getBoardByBoardId(Long boardId) {
        return convert.boardEntityToDTO(boardRepository.findByBoardId(boardId));
    }
}
