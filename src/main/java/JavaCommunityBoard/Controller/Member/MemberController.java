package JavaCommunityBoard.Controller.Member;

import JavaCommunityBoard.DTO.Member.MemberDTO;
import JavaCommunityBoard.Entity.Member.MemberEntity;
import JavaCommunityBoard.Repository.Member.MemberRepository;
import JavaCommunityBoard.Service.File.FileEncodedService;
import JavaCommunityBoard.Service.Member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping
public class MemberController {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;
    private final FileEncodedService fileEncodedService;


    @PostMapping(value = "/signUp", consumes = "multipart/form-data")
    public boolean registerUser(@RequestPart(value = "userInfo") String userInfo, @RequestPart(value = "profileImg") MultipartFile profileImg) {
        try {
            MemberDTO memberDTO = objectMapper.readValue(userInfo, MemberDTO.class);
             memberService.signUp(memberDTO,profileImg);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDTO memberDTO){
        return memberService.signIn(memberDTO);
    }

    @GetMapping("/getProfileImg/{memberId}")
    public String downloadMemberProfileImage(@PathVariable("memberId") long memberId) {
        System.out.println(memberService.loadMemberProfileImage(memberId));
        return memberService.loadMemberProfileImage(memberId);
    }

}
