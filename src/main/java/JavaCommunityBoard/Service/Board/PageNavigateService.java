package JavaCommunityBoard.Service.Board;

import JavaCommunityBoard.DTO.Board.BoardDTO;
import JavaCommunityBoard.Entity.Board.BoardEntity;
import JavaCommunityBoard.Repository.Board.PageNavigateRepository;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PageNavigateService implements PageNavigateServiceInterface{

    @Autowired
    private PageNavigateRepository navigateRepository;

    @Autowired
    private Convert convert;

    @Transactional
    @Override
    public List<BoardDTO> getPageSendBoards(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1 , 3, Sort.by("createdAt").descending());
        Page<BoardEntity> page = navigateRepository.findAll(pageable);
        System.out.println(page);
        return page.getContent().stream()
                .map(convert::boardEntityToDTO)
                .toList();
    }
}
