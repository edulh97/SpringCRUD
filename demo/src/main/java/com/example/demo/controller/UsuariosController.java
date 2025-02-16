package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Usuario> obtenerTodosLosUsusarios() {
        return usuariosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return  usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @PostMapping
    public Usuario crearUsuarios(@RequestBody Usuario newUsuario) {
        return usuariosRepository.save(newUsuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuarios(@PathVariable("id") Long id, @RequestBody Usuario detallesUsuarios) {
        Usuario updateUsuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto/Plato no encontrado"));
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
                .orElseThrow(() -> new ResourceNotFoundException("no existe"));
        usuariosRepository.deleteById(id);
        return usuario;
    }

}
