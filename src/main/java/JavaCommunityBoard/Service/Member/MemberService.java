package JavaCommunityBoard.Service.Member;

import JavaCommunityBoard.DTO.MemberDTO;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleDuplicateMember;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Paths.PathConstants;
import JavaCommunityBoard.Repository.MemberRepository;
import JavaCommunityBoard.Service.File.FileDownloadService;
import JavaCommunityBoard.Service.File.FileUploadService;
import JavaCommunityBoard.Utillity.JWT.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static JavaCommunityBoard.Paths.PathConstants.GET_MEMBERS_FILE_BASEURL;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final FileUploadService fileUploadService;
    private final FileDownloadService fileDownloadService;

    @Transactional
    @Override
    public boolean signUp(MemberDTO memberDTO, MultipartFile profileImg) throws IOException {
        if(memberRepository.existsByName(memberDTO.getName())) throw new HandleDuplicateMember("중복된 회원 입니다");
        String hashedPassword = passwordEncoder.encode(memberDTO.getPassword());
        MemberEntity memberEntity = new MemberEntity(memberDTO.getName(),hashedPassword,memberDTO.getNickname(),memberDTO.getRole());

        if (profileImg != null && !profileImg.isEmpty()) {
            String fileName = fileUploadService.storeFile(profileImg, GET_MEMBERS_FILE_BASEURL);
            memberEntity.setProfilePath(fileName);
        }
        memberRepository.save(memberEntity);
        return true;
    }

    @Transactional
    @Override
    public String signIn(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findByName(memberDTO.getName()).orElseThrow(() -> new HandleMisMatchUserInfo("존재 하지 않는 회원입니다"));
        if (!passwordEncoder.matches(memberDTO.getPassword(),memberEntity.getPassword())) throw new HandleMisMatchUserInfo("정보가 일치하지 않습니다");
        return jwtUtil.createJwt(memberEntity.getId(),memberEntity.getName(),memberEntity.getNickname(),memberEntity.getRole(),24*60*60*1000L);
    }

    @Transactional
    @Override
    public String loadMemberProfileImage(long memberId, String uploadDir)  {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(()->new HandleMisMatchUserInfo("존재 하지 않는 회원입니다"));
        if (memberEntity.getProfilePath() == null) {
            return "";
        }
        String fileName = memberEntity.getProfilePath();
        return fileDownloadService.loadFileAsResource(fileName,uploadDir);
    }


    public String getMemberNicknames(long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new HandleMisMatchUserInfo("존재 하지않는 회원입니다")).getNickname();
    }
}
