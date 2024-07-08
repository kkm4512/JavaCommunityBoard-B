package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findAllByOrderByCreatedAtDesc();
    List<BoardEntity> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
    List<BoardEntity> findByBoardIdIn(List<Long> boardIds);
    BoardEntity findByBoardId(Long boardId);
}
