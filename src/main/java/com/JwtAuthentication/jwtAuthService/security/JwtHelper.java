package com.JwtAuthentication.jwtAuthService.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private final String secret = "jhsfgkjhjkfdgkjhdjkghjdghkjhsjkhjhfjdkhfjksjkfh2ghg37ghfhgjfgjshgsbdfhdjfhsdkfjiwfibjsfsfsgjksdhgjfhdkjghdkhgkjsdgksjdbgkdjfbgjkdshgkjbgk";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public Boolean isTokenExpired(String token) {
        Date expiredDate = getExpirationDateFromToken(token);
        return expiredDate.before(new Date());
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes())).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Objects> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Objects> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = getUsernameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
