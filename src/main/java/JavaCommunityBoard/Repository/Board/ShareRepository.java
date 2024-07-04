package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<ShareEntity,Long> {
//    List<ShareEntity> findByLoginMemberIdOrderByBoardCreatedAtDesc(Long loginMemberId);
//    ShareEntity findByMember_IdAndIdAndBoard_BoardId(Long memberId, Long id, Long boardId);
}
