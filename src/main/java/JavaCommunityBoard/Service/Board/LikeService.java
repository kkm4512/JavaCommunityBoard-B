package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.LikeEntity;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.LikeRepository;
import JavaCommunityBoard.Repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService implements LikeServiceInterface{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void toggleLike(Long memberId, Long boardId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new HandleMisMatchUserInfo("해당 회원은 조회 되지 않습니다"));
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new HandleMisMatchUserInfo("해당 게시글은 조회 되지 않습니다"));
        Optional<LikeEntity> likeEntity = likeRepository.findByMemberEntityIdAndBoardEntityBoardId(memberId,boardId);
        if (likeEntity.isPresent()) likeRepository.delete(likeEntity.get());
        else {
            LikeEntity newLike = new LikeEntity();
            newLike.setMemberEntity(memberEntity);
            newLike.setBoardEntity(boardEntity);
            newLike.setLiked(true);
            likeRepository.save(newLike);
        }
    }
}
