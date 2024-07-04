package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.ShareDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.ShareEntity;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.ShareRepository;
import JavaCommunityBoard.Repository.MemberRepository;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ShareService implements ShareServiceInterface{

    private final Convert convert;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ShareRepository shareRepository;

    @Transactional
    @Override
    public boolean createShareBoard(ShareDTO shareDTO){
        ShareEntity shareEntity = new ShareEntity();

        shareEntity.setTitle(shareDTO.getTitle());
        shareEntity.setDescription(shareDTO.getDescription());
        shareEntity.setMemberId(shareDTO.getMemberId());
        shareEntity.setNickname(shareDTO.getNickname());
        shareEntity.setBoardImagePath(shareDTO.getBoardImagePath());
        shareEntity.setShared(shareDTO.isShared());
        shareEntity.setLoginMemberId(shareDTO.getLoginMemberId());
        shareEntity.setWriterImagePath(shareDTO.getWriterImagePath());

        shareRepository.save(shareEntity);

        return true;
    }

//    @Transactional
//    @Override
//    public List<ShareDTO> getShareBoards(Long loginMemberId) {
//        List<ShareEntity> shareEntities = shareRepository.findByLoginMemberIdOrderByBoardCreatedAtDesc(loginMemberId);
//        return shareEntities.stream().map(convert::shareEntityToDTO).toList();
//    }
//
//    @Override
//    public boolean removeBoard(ShareDTO shareDTO) {
//        MemberEntity memberEntity = memberRepository.findById(shareDTO.getBoard().getMemberId()).orElseThrow(() -> new IllegalArgumentException("올바르지 않은 회원입니다"));
//        BoardEntity boardEntity = boardRepository.findById(shareDTO.getBoard().getBoardId()).orElseThrow(() -> new IllegalArgumentException("올바르지 않은 게시글입니다"));
//        ShareEntity shareEntity = shareRepository.findByMember_IdAndIdAndBoard_BoardId(shareDTO.getLoginMemberId(),shareDTO.getId(),shareDTO.getBoard().getBoardId());
//        shareRepository.delete(shareEntity);
//        return true;
//    }
}
