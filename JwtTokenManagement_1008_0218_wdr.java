// 代码生成时间: 2025-10-08 02:18:20
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtTokenManagement class handles JWT token creation and validation.
 */
public class JwtTokenManagement {

    /**
     * The secret key for signing the JWT token.
     */
    private static final String SECRET_KEY = "your_secret_key";

    /**
     * The duration after which the token will expire.
     */
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour in milliseconds

    private JwtTokenManagement() {}

    /**
     * Generate a JWT token.
     *
     * @param username The username to be included in the token.
     * @return A JWT token as a string.
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Validate a JWT token.
     *
     * @param token The token to be validated.
     * @return The username if the token is valid, otherwise null.
     */
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null; // Token is invalid or expired.
        }
    }

    /**
     * Refresh a JWT token.
     *
     * @param oldToken The old token to be refreshed.
     * @return A new JWT token as a string.
     */
    public static String refreshToken(String oldToken) {
        String username = validateToken(oldToken);
        if (username != null) {
            return generateToken(username);
        } else {
            throw new IllegalArgumentException("Invalid or expired token provided for refresh");
        }
    }
}
