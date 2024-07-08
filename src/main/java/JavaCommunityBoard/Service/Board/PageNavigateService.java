package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import JavaCommunityBoard.Entity.Board.ShareEntity;
import JavaCommunityBoard.Repository.Board.BoardRepository;
import JavaCommunityBoard.Repository.Board.PageNavigateRepository;
import JavaCommunityBoard.Repository.Board.ShareRepository;
import JavaCommunityBoard.Repository.Inquiry.InquiryRepository;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PageNavigateService implements PageNavigateServiceInterface{
    private final ShareRepository shareRepository;
    private final BoardRepository boardRepository;
    private final ShareService shareService;
    private final InquiryRepository inquiryRepository;

    @Autowired
    private PageNavigateRepository navigateRepository;

    @Autowired
    private Convert convert;

    @Transactional
    @Override
    public List<BoardDTO> getPageSendBoards(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1 , 3, Sort.by("createdAt").descending());
        Page<BoardEntity> page = navigateRepository.findAll(pageable);
        return page.getContent().stream()
                .map(convert::boardEntityToDTO)
                .toList();
    }

    @Transactional
    @Override
    public List<BoardDTO> getLoginMemberIdAndPageSendBoards(int pageNumber, Long loginMemberId) {
        Pageable pageable = PageRequest.of(pageNumber-1,3,Sort.by("createdAt").descending());
        Page<BoardEntity> page = navigateRepository.findByMemberId(loginMemberId,pageable);
        return page.getContent().stream()
                .map(convert::boardEntityToDTO)
                .toList();

    }

    @Transactional
    @Override
    public List<BoardDTO> getLoginMemberIdAndPageSendShareBoards(int pageNumber, Long loginMemberId) {
        Pageable pageable = PageRequest.of(pageNumber - 1 , 3, Sort.by("id").descending());
        Page<ShareEntity> page = shareRepository.findAllByLoginMemberIdOrderByIdDesc(loginMemberId,pageable);
        return page.getContent().stream()
                .map(share -> convert.sharedBoardEntityToDTO(share.getBoardEntity()))
                .toList();
    }

    @Transactional
    @Override
    public List<BoardDTO> getAllShareBoardsAndBoards(int pageNumber, Long loginMemberId){
        List<BoardDTO> boardList = getPageAllBoardsById(loginMemberId);
        List<BoardDTO> shareBoardList = getPageAllShareBoardsById(loginMemberId);
        List<BoardDTO> combinationBoardLists = new ArrayList<>(boardList);
        combinationBoardLists.addAll(shareBoardList);
        Pageable pageable = PageRequest.of(pageNumber - 1, 3, Sort.by("createdAt").descending());

        List<BoardDTO> sortedList = combinationBoardLists.stream()
                .sorted(Comparator.comparing(BoardDTO::getCreatedAt).reversed())
                .toList();

        // 페이지화된 서브리스트를 생성합니다.
        int fromIndex = (int) pageable.getOffset();
        int toIndex = Math.min((fromIndex + pageable.getPageSize()), sortedList.size());

        if (fromIndex > sortedList.size()) {
            return new ArrayList<>(); // 페이지 번호가 범위를 벗어나는 경우 빈 리스트 반환
        }

        return sortedList.subList(fromIndex, toIndex);

    }

    @Transactional
    @Override
    public List<BoardDTO> getPageAllBoardsById(Long loginMemberId) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("createdAt").descending());
        Page<BoardEntity> page = navigateRepository.findByMemberId(loginMemberId, pageable);
        return page.getContent().stream()
                .map(convert::boardEntityToDTO)
                .toList();
    }

    @Transactional
    @Override
    public List<BoardDTO> getPageAllShareBoardsById(Long loginMemberId) {
        return shareService.getSharedBoards(loginMemberId);
    }

    @Transactional
    @Override
    public List<InquiryDTO> getAllInquiries(int PageNumber) {
        Pageable pageable = PageRequest.of(PageNumber - 1,3,Sort.by("createdAt").ascending());
        Page<InquiryEntity> inquiryEntityPage = inquiryRepository.findAllByOrderByCreatedAtAsc(pageable);
        return inquiryEntityPage.getContent().stream()
                .map(convert::inquiryEntityToDTO)
                .toList();
    }
}
