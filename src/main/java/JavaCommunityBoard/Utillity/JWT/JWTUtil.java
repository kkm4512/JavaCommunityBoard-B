package JavaCommunityBoard.Utillity.JWT;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    private SecretKey secretKey;


    public JWTUtil(@Value("${jwt.secret}")String secret){
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getName(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("name",String.class);
    }

    public String getNickname(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("nickname",String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role",String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(Long id, String name, String nickname, String role, Long expiredMs){
        return Jwts.builder()
                .claim("name",name)
                .claim("role",role)
                .claim("nickname",nickname)
                .claim("id",id)
                //언제발행
                .issuedAt(new Date(System.currentTimeMillis()))
                //만료기한
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

}
