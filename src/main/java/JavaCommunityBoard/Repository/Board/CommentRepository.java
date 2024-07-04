package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    Optional<CommentEntity> findByMemberEntityIdAndBoardEntityBoardId(Long memberId, Long boardId);
    Optional<CommentEntity> findByMemberEntityIdAndBoardEntityBoardIdAndId(Long memberId, Long boardId, Long id);
}
