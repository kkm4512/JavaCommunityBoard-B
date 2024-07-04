package JavaCommunityBoard.Controller;

import JavaCommunityBoard.DTO.MemberDTO;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Paths.PathConstants;
import JavaCommunityBoard.Service.File.FileEncodedService;
import JavaCommunityBoard.Service.Member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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
    private final Validator validator;


    @SneakyThrows
    @PostMapping(value = "/signUp", consumes = "multipart/form-data")
    public boolean registerUser(@Valid @RequestPart(value = "userInfo") MemberDTO memberDTO, @RequestPart(value = "profileImg", required = false) MultipartFile profileImg) {
        memberService.signUp(memberDTO,profileImg);
        return true;
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDTO memberDTO){
        return memberService.signIn(memberDTO);
    }

    @GetMapping("/getProfileImg/{memberId}")
    public String downloadMemberProfileImage(@PathVariable("memberId") long memberId) {
        return memberService.loadMemberProfileImage(memberId, PathConstants.GET_MEMBERS_FILE_BASEURL);
    }

    @GetMapping("/getNickname/{memberId}")
    public String getByMemberIdSendMemberNickname(@PathVariable("memberId") long  memberId) {
        return memberService.getMemberNicknames(memberId);
    }
}
