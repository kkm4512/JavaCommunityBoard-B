package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.CommentDTO;

public interface CommentServiceInterface {
    boolean setComments(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);

    void removeComment(CommentDTO commentDTO);
}
