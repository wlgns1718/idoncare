package d209.Idontcare.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static boolean isExpired(String accessToken, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken)
                .getBody().getExpiration().before(new Date());
    }

    public static String createToken(String userId, String secretKey,Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userId",userId); // 토큰에 담을 정보

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256,secretKey) // ES256 알고리즘으로 암호화
                .compact();
    }

}
