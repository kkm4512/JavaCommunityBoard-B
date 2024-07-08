package JavaCommunityBoard.Utillity.Convert;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.CommentDTO;
import JavaCommunityBoard.DTO.Board.LikeDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.CommentEntity;
import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import JavaCommunityBoard.Entity.Board.LikeEntity;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Paths.PathConstants;
import JavaCommunityBoard.Service.File.FileDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Convert {

    private final FileDownloadService fileDownloadService;
    public BoardDTO boardEntityToDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setMemberId(boardEntity.getMemberId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setDescription(boardEntity.getDescription());
        boardDTO.setNickname(boardEntity.getNickname());
        boardDTO.setBoardImagePath(boardEntity.getBoardImagePath());
        boardDTO.setCreatedAt(boardEntity.getCreatedAt());
        boardDTO.setUpdatedAt(boardEntity.getUpdatedAt());

        List<LikeDTO> likeDTOS = boardEntity.getLikes().stream()
                .map( this::likeEntityToDTO )
                .toList();
        boardDTO.setLikes(likeDTOS);

        List<CommentDTO> commentDTOS = boardEntity.getComments().stream()
                .map( this::commentEntityToDTO )
                .toList();
        boardDTO.setComments(commentDTOS);
        return boardDTO;
    }



    public LikeDTO likeEntityToDTO(LikeEntity likeEntity){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setMemberId(likeEntity.getMemberEntity().getId());
        likeDTO.setBoardId(likeEntity.getBoardEntity().getBoardId());
        likeDTO.setLiked(likeEntity.getLiked());
        return likeDTO;
    }

    public LikeEntity likeDtoToEntity(LikeDTO likeDTO){
        LikeEntity likeEntity = new LikeEntity();
        MemberEntity memberEntity = new MemberEntity();
        BoardEntity boardEntity = new BoardEntity();
        likeEntity.setMemberEntity(memberEntity);
        likeEntity.setBoardEntity(boardEntity);
        likeEntity.setLiked(likeDTO.getLiked());
        return likeEntity;
    }

    public CommentDTO commentEntityToDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setMemberId(commentEntity.getMemberEntity().getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getBoardId());
        commentDTO.setCreatedAt(commentEntity.getCreatedAt());
        commentDTO.setUpdatedAt(commentEntity.getUpdatedAt());
        commentDTO.setComment(commentEntity.getComment());
        return commentDTO;
    }

    public CommentEntity commentDtoToEntity(CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        MemberEntity memberEntity = new MemberEntity();
        BoardEntity boardEntity = new BoardEntity();
        commentEntity.setId(commentDTO.getId());
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setComment(commentDTO.getComment());
        return commentEntity;
    }

    public BoardEntity boardDtoToEntity (BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardId(boardDTO.getBoardId());
        boardEntity.setMemberId(boardDTO.getMemberId());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setDescription(boardDTO.getDescription());
        boardEntity.setNickname(boardDTO.getNickname());
        boardEntity.setBoardImagePath(boardDTO.getBoardImagePath());
        boardEntity.setUpdatedAt(boardDTO.getUpdatedAt());
        boardEntity.setCreatedAt(boardDTO.getCreatedAt());

        List<CommentDTO> commentDTOS = boardEntity.getComments().stream()
                .map( this::commentEntityToDTO )
                .toList();
        boardDTO.setComments(commentDTOS);

        List<LikeDTO> likeDTOS = boardEntity.getLikes().stream()
                .map( this::likeEntityToDTO)
                .toList();
        boardDTO.setLikes(likeDTOS);
        return boardEntity;
    }

    public BoardDTO sharedBoardEntityToDTO(BoardEntity boardEntity, Long sharedId){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setMemberId(boardEntity.getMemberId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setDescription(boardEntity.getDescription());
        boardDTO.setNickname(boardEntity.getNickname());
        boardDTO.setBoardImagePath(boardEntity.getBoardImagePath());
        boardDTO.setCreatedAt(boardEntity.getCreatedAt());
        boardDTO.setUpdatedAt(boardEntity.getUpdatedAt());
        boardDTO.setShared(true);
        boardDTO.setSharedId(sharedId);

        List<LikeDTO> likeDTOS = boardEntity.getLikes().stream()
                .map( this::likeEntityToDTO )
                .toList();
        boardDTO.setLikes(likeDTOS);

        List<CommentDTO> commentDTOS = boardEntity.getComments().stream()
                .map( this::commentEntityToDTO )
                .toList();
        boardDTO.setComments(commentDTOS);
        return boardDTO;
    }

    public BoardDTO sharedBoardEntityToDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardEntity.getBoardId());
        boardDTO.setMemberId(boardEntity.getMemberId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setDescription(boardEntity.getDescription());
        boardDTO.setNickname(boardEntity.getNickname());
        boardDTO.setBoardImagePath(boardEntity.getBoardImagePath());
        boardDTO.setCreatedAt(boardEntity.getCreatedAt());
        boardDTO.setUpdatedAt(boardEntity.getUpdatedAt());
        boardDTO.setShared(true);

        List<LikeDTO> likeDTOS = boardEntity.getLikes().stream()
                .map( this::likeEntityToDTO )
                .toList();
        boardDTO.setLikes(likeDTOS);

        List<CommentDTO> commentDTOS = boardEntity.getComments().stream()
                .map( this::commentEntityToDTO )
                .toList();
        boardDTO.setComments(commentDTOS);
        return boardDTO;
    }

    public InquiryDTO inquiryEntityToDTO(InquiryEntity inquiryEntity){
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setId(inquiryEntity.getId());
        inquiryDTO.setTitle(inquiryEntity.getTitle());
        inquiryDTO.setDescription(inquiryEntity.getDescription());
        inquiryDTO.setMemberId(inquiryEntity.getMemberId());
        inquiryDTO.setCategory(inquiryEntity.getCategory());
        inquiryDTO.setCreatedAt(inquiryEntity.getCreatedAt());
        if ( inquiryEntity.getFilePath() != null && !inquiryEntity.getFilePath().isEmpty()) {
            String filePath = fileDownloadService.loadFileAsResource(inquiryEntity.getFilePath(), PathConstants.GET_INQUIRY_FILE_BASEURL);
            inquiryDTO.setFilePath(filePath);
        }
        return inquiryDTO;
    }



}
