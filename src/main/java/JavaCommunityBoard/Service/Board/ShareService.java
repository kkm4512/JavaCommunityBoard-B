package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Board.ShareDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.Board.ShareEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.ShareRepository;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ShareService implements ShareServiceInterface{

    private final ShareRepository shareRepository;
    private final BoardRepository boardRepository;
    private final Convert convert;

    @Transactional
    @Override
    public List<BoardDTO> getSharedBoards(Long loginMemberId) {
        List<ShareEntity> shares = shareRepository.findAllByLoginMemberIdOrderByIdDesc(loginMemberId);
        List<BoardEntity> boardEntities = shares.stream()
                .map(ShareEntity::getBoardEntity)
                .toList();
        return shares.stream()
                .map(share -> convert.sharedBoardEntityToDTO(share.getBoardEntity(), share.getId()))
                .toList();
    }

    @Override
    public boolean removeSharedBoard(ShareDTO shareDTO) {
        ShareEntity shareEntity = shareRepository.findById(shareDTO.getSharedId()).orElseThrow(() -> new HandleMisMatchBoardInfo("없습니다"));
        shareRepository.delete(shareEntity);
        return true;
    }
}
