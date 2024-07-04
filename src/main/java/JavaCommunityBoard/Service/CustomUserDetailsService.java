package JavaCommunityBoard.Service;

import JavaCommunityBoard.DTO.CustomUserDetails;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByName(name).orElseThrow(() -> new HandleMisMatchUserInfo("사용자의 정보가 일치하지 않습니다"));
        MemberEntity member = new MemberEntity(memberEntity.getName(),memberEntity.getPassword());
        return new CustomUserDetails(memberEntity);
    }
}
