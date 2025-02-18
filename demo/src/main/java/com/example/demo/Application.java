package com.example.demo;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.security.JWTAuthorizationFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import java.util.Collections;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Funciona socio");
    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthorizationFilter jwtAuthorizationFilter)
                throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/Login-Screen").permitAll()
                    .anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults())
                    .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService(UsuariosRepository usuariosRepository) {
            return correoElectronico -> {
                Usuario usuario = usuariosRepository.findByCorreoElectronico(correoElectronico)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "Usuario no encontrado con el correo: " + correoElectronico));

                return new org.springframework.security.core.userdetails.User(
                        usuario.getCorreoElectronico(),
                        usuario.getContrasena(),
                        Collections.singletonList(
                                new org.springframework.security.core.authority.SimpleGrantedAuthority(
                                        "ROLE_" + usuario.getTipoUsuario())));
            };
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(); // Usa BCrypt para codificar contraseñas
        }

        @Bean
        public JWTAuthorizationFilter jwtAuthorizationFilter(String jwtSecret) {
            return new JWTAuthorizationFilter(jwtSecret);
        }
    }
}
