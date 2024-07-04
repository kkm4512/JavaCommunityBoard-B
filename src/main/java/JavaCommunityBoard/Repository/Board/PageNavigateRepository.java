package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageNavigateRepository extends JpaRepository<BoardEntity,Long> {
    Page<BoardEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
