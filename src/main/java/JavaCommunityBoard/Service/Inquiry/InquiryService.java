package JavaCommunityBoard.Service.Inquiry;

import JavaCommunityBoard.DTO.Inquiry.InquiryDTO;
import JavaCommunityBoard.DTO.Inquiry.InquirySaveDTO;
import JavaCommunityBoard.Entity.inquiry.InquiryEntity;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchBoardInfo;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Paths.PathConstants;
import JavaCommunityBoard.Repository.Inquiry.InquiryRepository;
import JavaCommunityBoard.Repository.MemberRepository;
import JavaCommunityBoard.Service.File.FileUploadService;
import JavaCommunityBoard.Utillity.Convert.Convert;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService implements InquiryServiceInterface{
    private final MemberRepository memberRepository;
    private final InquiryRepository inquiryRepository;
    private final Convert convert;
    private final FileUploadService fileUploadService;


    @SneakyThrows
    @Transactional
    @Override
    public void saveInquiry(InquirySaveDTO inquirySaveDTO, MultipartFile saveInquiryImage) {
        MemberEntity memberEntity = memberRepository.findById(inquirySaveDTO.getMemberId()).orElseThrow(() -> new HandleMisMatchUserInfo("조회되는 회원이 없습니다"));
        String saveInquiryImagePath = null;
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setTitle(inquirySaveDTO.getTitle());
        inquiryEntity.setDescription(inquirySaveDTO.getDescription());
        inquiryEntity.setCategory(inquirySaveDTO.getCategory());
        inquiryEntity.setMemberId(inquirySaveDTO.getMemberId());
        inquiryEntity.setCreatedAt(inquirySaveDTO.getCreatedAt());
        if (saveInquiryImage != null && !saveInquiryImage.isEmpty()) {
            saveInquiryImagePath = fileUploadService.storeFile(saveInquiryImage,PathConstants.GET_INQUIRY_FILE_BASEURL);
            inquiryEntity.setFilePath(saveInquiryImagePath);
        }
        inquiryRepository.save(inquiryEntity);
    }

    @Transactional
    @Override
    public List<InquiryDTO> getAllInquires() {
        List<InquiryEntity> inquiryEntityList = inquiryRepository.findAllByOrderByCreatedAtAsc();
        return inquiryEntityList.stream()
                .map(convert::inquiryEntityToDTO)
                .toList();
    }

    @Transactional
    @Override
    public boolean deleteInquiry(Long id) {
        InquiryEntity inquiryEntity = inquiryRepository.findById(id).orElseThrow(() -> new HandleMisMatchBoardInfo("해당 문의글을 찾을 수 없습니다"));
        inquiryRepository.delete(inquiryEntity);
        return true;
    }
}
