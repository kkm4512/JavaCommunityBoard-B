package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.CommentDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.CommentEntity;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.CommentRepository;
import JavaCommunityBoard.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService implements  CommentServiceInterface{
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public boolean setComments(CommentDTO commentDTO) {
        MemberEntity memberEntity = memberRepository.findById(commentDTO.getMemberId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 회원은 존재하지 않습니다"));
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 게시글은 존재하지 않습니다"));
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setComment(commentDTO.getComment());
        commentRepository.save(commentEntity);
        return true;
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        MemberEntity memberEntity = memberRepository.findById(commentDTO.getMemberId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 회원은 존재하지 않습니다"));
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 게시글은 존재하지 않습니다"));
        CommentEntity commentEntity = commentRepository.findByMemberEntityIdAndBoardEntityBoardIdAndId(memberEntity.getId(),boardEntity.getBoardId(),commentDTO.getId()).orElseThrow(() -> new HandleMisMatchBoardInfo("조회 되지 않습니다"));
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setComment(commentDTO.getComment());
        commentRepository.save(commentEntity);
    }

    @Override
    public void removeComment(CommentDTO commentDTO) {
        MemberEntity memberEntity = memberRepository.findById(commentDTO.getMemberId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 회원은 존재하지 않습니다"));
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new HandleMisMatchUserInfo("요청 하신 게시글은 존재하지 않습니다"));
        CommentEntity commentEntity = commentRepository.findByMemberEntityIdAndBoardEntityBoardIdAndId(memberEntity.getId(),boardEntity.getBoardId(),commentDTO.getId()).orElseThrow(() -> new HandleMisMatchBoardInfo("조회 되지 않습니다"));
        commentRepository.delete(commentEntity);
    }
}
