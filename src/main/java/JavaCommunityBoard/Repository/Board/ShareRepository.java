package JavaCommunityBoard.Repository.Board;

import JavaCommunityBoard.Entity.Board.ShareEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
    List<ShareEntity> findAllByLoginMemberIdOrderByIdDesc(Long loginMemberId);
    Page<ShareEntity> findAllByLoginMemberIdOrderByIdDesc(Long loginMemberId, Pageable pageable);

}

