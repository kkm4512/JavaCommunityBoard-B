package JavaCommunityBoard.Utillity.JWT;

import JavaCommunityBoard.DTO.CustomUserDetails;
import JavaCommunityBoard.Entity.Member.MemberEntity;
import JavaCommunityBoard.Exceptions.HandleMisMatchUserInfo;
import JavaCommunityBoard.Repository.Member.MemberRepository;
import JavaCommunityBoard.types.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter  {

    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        String authorization = req.getHeader("Authorization");


            //토큰 확인
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                logger.info("token null");
                filterChain.doFilter(req,res);
                return;
            }

            String token = authorization.split(" ")[1];

            //토큰 소멸 시간 검증
            if (jwtUtil.isExpired(token)) {
                logger.info("token expired");
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
