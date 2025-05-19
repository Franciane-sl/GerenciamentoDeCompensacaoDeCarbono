package com.futurodevV2.GerenciamentoDeCarbono.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("476e11b5f61337aeda63ea343fda20a4effa0ef671efe26c8bed3384d842051d4c1a22679ffd864d8906e8daf068f4c7649414a017ec10635cdc72ee0d9b01e1484ea8ec2e5e3daed32576ac4e4535eec6ff045bf4dc8edacb467ea78002f6d19c5e87bd53a6c09d677f7d6d919de777776a8eebd2a9423a9f9c2bcf41cac952".getBytes());

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = Map.of(
                "roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public List<String> extractRoles(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", List.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        try{
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }catch(Exception e){
            return false;
        }
    }

    public String extractUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenExpired(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration().before(new Date());
    }
}