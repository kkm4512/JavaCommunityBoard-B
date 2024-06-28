package JavaCommunityBoard.Service.Member;

import JavaCommunityBoard.DTO.Member.MemberDTO;
import JavaCommunityBoard.Entity.Member.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleDuplicateMember;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Repository.Member.MemberRepository;
import JavaCommunityBoard.Service.File.FileDownloadService;
import JavaCommunityBoard.Service.File.FileUploadService;
import JavaCommunityBoard.Utillity.JWT.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceInterface{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final FileUploadService fileUploadService;
    private final FileDownloadService fileDownloadService;

    @Override
    @Transactional
    public boolean signUp(MemberDTO memberDTO, MultipartFile profileImg) throws IOException {
        if(memberRepository.existsByName(memberDTO.getName())) throw new HandleDuplicateMember("중복된 회원 입니다");
        String hashedPassword = passwordEncoder.encode(memberDTO.getPassword());
        MemberEntity memberEntity = new MemberEntity(memberDTO.getName(),hashedPassword,memberDTO.getNickname(),memberDTO.getRole());

        if (!profileImg.isEmpty()) {
            String fileName = fileUploadService.storeFile(profileImg);
            memberEntity.setMultipartFilePath(fileName);
        }

        memberRepository.save(memberEntity);
        return true;
    }

    @Override
    @Transactional
    public String signIn(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findByName(memberDTO.getName()).orElseThrow(() -> new HandleMisMatchUserInfo("존재 하지 않는 회원입니다"));
        if (!passwordEncoder.matches(memberDTO.getPassword(),memberEntity.getPassword())) throw new HandleMisMatchUserInfo("정보가 일치하지 않습니다");
        return jwtUtil.createJwt(memberEntity.getId(),memberEntity.getName(),memberEntity.getNickname(),memberEntity.getRole(),24*60*60*1000L);
    }

    @Override
    public String loadMemberProfileImage(long memberId)  {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(()->new HandleMisMatchUserInfo("존재 하지 않는 회원입니다"));
        String fileName = memberEntity.getMultipartFilePath();
        return fileDownloadService.loadFileAsResource(fileName);
    }


}
