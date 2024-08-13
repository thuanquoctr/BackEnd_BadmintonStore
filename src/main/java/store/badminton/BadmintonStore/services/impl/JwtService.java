package store.badminton.BadmintonStore.services.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    public static final String JWT_SECRET = "3KHKCG54GH5VH43V5HJ7BJKBJB65JB2J6BJ46BJKBJB65JB2J6BJ46BJKBJB65JB2J6BJ46BJKBJB65JB2J6BJ46B";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("username", username);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, getPublicKey())
                .compact();
    }

    private Key getPublicKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(getPublicKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsTFunc) {
        final Claims claims = getAllClaims(token);
        return claimsTFunc.apply(claims);
    }

    //getDateToken
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    //getUsernameToken
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    //checkdatedie
    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
