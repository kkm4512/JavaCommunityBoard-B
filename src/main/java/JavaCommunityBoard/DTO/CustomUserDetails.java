package JavaCommunityBoard.DTO;

import JavaCommunityBoard.Entity.Member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final MemberEntity memberEntity;

    @Override
    //Role return
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getName();
    }

    public String getNickname(){
        return memberEntity.getNickname();
    }

    public Long getId(){
        return memberEntity.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
