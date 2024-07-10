package JavaCommunityBoard.Utillity.JWT;

import JavaCommunityBoard.DTO.CustomUserDetails;
import JavaCommunityBoard.Entity.MemberEntity;
import JavaCommunityBoard.Repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter  {

    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final List<String> excludedPaths = Arrays.asList(
            "/board/**",
            "/login",
            "/signUp",
            "/getProfileImg/**",
            "/uploads/**",
            "/like/**",
            "/getNickname/**"
    );

    private boolean isExcluded(String requestURI) {
        return excludedPaths.stream().anyMatch(p -> pathMatcher.match(p, requestURI));
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String authorization = req.getHeader("Authorization");
        String requestURI = req.getRequestURI();
        String path = req.getRequestURI();

        if (isExcluded(requestURI)) {
            filterChain.doFilter(req, res);
            return;
        }

        // 특정 경로를 제외하도록 설정
        if (pathMatcher.match("/board/getBoards", path)) {
            filterChain.doFilter(req, res);
            return;
        }

            //토큰 확인
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                filterChain.doFilter(req,res);
                return;
            }

            String token = authorization.split(" ")[1];

            //토큰 소멸 시간 검증
            if (jwtUtil.isExpired(token)) {
                filterChain.doFilter(req,res);
                return;
            }

            String name = jwtUtil.getName(token);
            String role = jwtUtil.getRole(token);



            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setName(name);
            memberEntity.setPassword("123412312");
            memberEntity.setRole(role);

            //UserDetails 에 회원 정보 객체 담기
            CustomUserDetails customUserDetails = new CustomUserDetails(memberEntity);

            //스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(req,res);



    }
}
