package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    Optional<LikeEntity> findByMemberEntityIdAndBoardEntityBoardId (Long memberId, Long boardId);
}
