package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/Login-Screen")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario foundUser = usuariosRepository
                .findByCorreoElectronicoAndContrasena(usuario.getCorreoElectronico(), usuario.getContrasena())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        String token = jwtTokenUtil.generateToken(foundUser.getCorreoElectronico(), foundUser.getTipoUsuario());

        // 🔹 Imprimir el token en la consola
        System.out.println("Token generado: " + token);

        foundUser.setToken(token);
        usuariosRepository.save(foundUser);

        return ResponseEntity.ok(token);
    }
}