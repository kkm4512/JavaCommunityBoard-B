package JavaCommunityBoard.Service.Member;

import JavaCommunityBoard.DTO.MemberDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemberServiceInterface {
    /***
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    public boolean signUp(MemberDTO memberDTO, MultipartFile profileImg) throws IOException;
    public String signIn(MemberDTO memberDTO);
    public String loadMemberProfileImage(long memberId,String uploadDir);
    public String getMemberNicknames(long memberId);
}
