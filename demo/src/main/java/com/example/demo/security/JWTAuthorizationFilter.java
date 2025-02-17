package com.example.demo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final SecretKey key;

    public JWTAuthorizationFilter(String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (checkJWTToken(request)) {
                System.out.println("✅ Token encontrado en la cabecera");

                Claims claims = validateToken(request);
                System.out.println("✅ Token validado con éxito: " + claims);

                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                    System.out.println("✅ Autenticación configurada en SecurityContext");
                } else {
                    System.out.println("❌ No se encontraron roles en el token, limpiando contexto de seguridad");
                    SecurityContextHolder.clearContext();
                }
            } else {
                System.out.println("❌ No se encontró token en la cabecera");
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            System.out.println("❌ Error al validar el token: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        Object authoritiesObject = claims.get("authorities");

        List<String> authorities = new ArrayList<>();

        if (authoritiesObject instanceof String) {
            // Si es un String, lo convertimos en una lista de un solo elemento
            authorities = List.of((String) authoritiesObject);
        } else if (authoritiesObject instanceof List<?>) {
            // Si es una lista, usamos ObjectMapper para hacer la conversión segura
            ObjectMapper mapper = new ObjectMapper();
            authorities = mapper.convertValue(authoritiesObject, new TypeReference<List<String>>() {
            });
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }
}
