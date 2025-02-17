package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Value("${jwt.secret}") // Inyectar la clave desde application.properties
    private String SECRET;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario newUsuario) {
        return usuariosRepository.save(newUsuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario detallesUsuario) {
        Usuario updateUsuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        updateUsuario.setNombreCompleto(detallesUsuario.getNombreCompleto());
        updateUsuario.setCorreoElectronico(detallesUsuario.getCorreoElectronico());
        updateUsuario.setDireccion(detallesUsuario.getDireccion());
        updateUsuario.setContrasena(detallesUsuario.getContrasena());
        updateUsuario.setTarjeta(detallesUsuario.getTarjeta());
        updateUsuario.setTipoUsuario(detallesUsuario.getTipoUsuario());
        updateUsuario.setToken(detallesUsuario.getToken());

        return usuariosRepository.save(updateUsuario);
    }

    @DeleteMapping("/{id}")
    public Usuario eliminarUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuariosRepository.deleteById(id);
        return usuario;
    }
}