package d209.Idontcare.jwt;


import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "i.don.care";

    public static boolean isExpired(String accessToken, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken)
                .getBody().getExpiration().before(new Date());
    }

    public static String createToken(String userId, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("userId",userId); // 토큰에 담을 정보

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY) // ES256 알고리즘으로 암호화
                .compact();
    }

    public static String getUserId(String token){

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().get("userId",String.class);
    }
}
