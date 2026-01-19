package car_pooling.security;

import io.jsonwebtoken.Jwts;
import java.util.Date;

public class JwtUtil {
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 12*60*60*1000))
                .compact();
    }
}
