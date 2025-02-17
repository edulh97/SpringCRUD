package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String correoElectronico, String tipoUsuario) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

        return Jwts.builder()
                .setSubject(correoElectronico)
                .claim("authorities", tipoUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de validez
                .signWith(key)
                .compact();
    }
}
