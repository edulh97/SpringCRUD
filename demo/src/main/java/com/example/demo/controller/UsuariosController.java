package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
=======
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;

<<<<<<< HEAD
import jakarta.validation.Valid;

=======
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

<<<<<<< HEAD
    @Value("${jwt.secret}")
    private String SECRET;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
=======
    @GetMapping
    public List<Usuario> obtenerTodosLosUsusarios() {
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
        return usuariosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @PostMapping
<<<<<<< HEAD
    public Usuario crearUsuario(@Valid @RequestBody Usuario newUsuario) {
=======
    public Usuario crearUsuarios(@RequestBody Usuario newUsuario) {
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
        return usuariosRepository.save(newUsuario);
    }

    @PutMapping("/{id}")
<<<<<<< HEAD
    public Usuario actualizarUsuario(
            @PathVariable("id") Long id,
            @Valid @RequestBody Usuario detallesUsuario) {
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
=======
    public Usuario actualizarUsuarios(@PathVariable("id") Long id, @RequestBody Usuario detallesUsuarios) {
        Usuario updateUsuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        updateUsuarios.setNombreCompleto(detallesUsuarios.getNombreCompleto());
        updateUsuarios.setCorreoElectronico(detallesUsuarios.getCorreoElectronico());
        updateUsuarios.setDireccion(detallesUsuarios.getDireccion());
        updateUsuarios.setContrasena(detallesUsuarios.getContrasena());
        updateUsuarios.setTarjeta(detallesUsuarios.getTarjeta());
        updateUsuarios.setTipoUsuario(detallesUsuarios.getTipoUsuario());
        updateUsuarios.setToken(detallesUsuarios.getToken());

        return usuariosRepository.save(updateUsuarios);
    }

    @DeleteMapping("/{id}")
    public Usuario eliminarUsuarios(@PathVariable("id") Long id) {
        Usuario usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ese usuario no existe"));
        usuariosRepository.deleteById(id);
        return usuario;
    }

}
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
