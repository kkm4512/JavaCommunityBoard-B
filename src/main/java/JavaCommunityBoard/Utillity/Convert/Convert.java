package JavaCommunityBoard.Utillity.Convert;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.CommentDTO;
import JavaCommunityBoard.DTO.Board.LikeDTO;
import JavaCommunityBoard.DTO.Board.ShareDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.CommentEntity;
import JavaCommunityBoard.Entity.Board.LikeEntity;
import JavaCommunityBoard.Entity.Board.ShareEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Convert {
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

    private LikeDTO likeEntityToDTO(LikeEntity likeEntity){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setMemberId(likeEntity.getMemberEntity().getId());
        likeDTO.setBoardId(likeEntity.getBoardEntity().getBoardId());
        likeDTO.setLiked(likeEntity.getLiked());
        return likeDTO;
    }

    private CommentDTO commentEntityToDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setMemberId(commentEntity.getMemberEntity().getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getBoardId());
        commentDTO.setComment(commentEntity.getComment());
        return commentDTO;
    }

//    public ShareDTO shareEntityToDTO (ShareEntity shareEntity){
//        ShareDTO shareDTO = new ShareDTO();
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setTitle(shareEntity.getBoard().getTitle());
//        boardDTO.setDescription(shareEntity.getBoard().getDescription());
//        boardDTO.setBoardId(shareEntity.getBoard().getBoardId());
//        boardDTO.setMemberId(shareEntity.getBoard().getMemberId());
//        boardDTO.setNickname(shareEntity.getBoard().getNickname());
//        boardDTO.setBoardImagePath(shareEntity.getBoard().getBoardImagePath());
//        boardDTO.setCreatedAt(shareEntity.getBoard().getCreatedAt());
//        boardDTO.setUpdatedAt(shareEntity.getBoard().getUpdatedAt());
//        shareDTO.setShared(shareEntity.isShared());
//        shareDTO.setLoginMemberId(shareEntity.getLoginMemberId());
//        shareDTO.setWriterImagePath(shareEntity.getWriterImagePath());
//
//        return shareDTO;
//    }


}
