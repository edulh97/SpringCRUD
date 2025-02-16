package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.TelefonosUsuario;
import com.example.demo.model.TelefonosUsuarioId;
import com.example.demo.repository.TelefonosUsuarioRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/telefonos_usuarios")
public class TelefonosUsuarioController {

    @Autowired
    private TelefonosUsuarioRepository telefonosUsuarioRepository;

    @GetMapping
    public List<TelefonosUsuario> obtenerTodosLosTelefonos() {
        return telefonosUsuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<TelefonosUsuario> obtenerTelefonosPorIdUsuario(@PathVariable("id") Long id) {
        return telefonosUsuarioRepository.findByIdUsuario(id);
    }

    @PostMapping
    public TelefonosUsuario crearTelefono(@RequestBody TelefonosUsuario newTelefono) {
        return telefonosUsuarioRepository.save(newTelefono);
    }

    @PostMapping("/{id}")
    public TelefonosUsuario crearTelefonoPorId(@PathVariable("id") Long id,
            @RequestBody TelefonosUsuario newTelefonosUsuario) {
        newTelefonosUsuario.setIdUsuario(id);
        return telefonosUsuarioRepository.save(newTelefonosUsuario);
    }

    @DeleteMapping
    public TelefonosUsuario eliminarTelefono(@RequestBody TelefonosUsuario telefonoEliminar) {
        telefonosUsuarioRepository.delete(telefonoEliminar);
        return telefonoEliminar;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTelefonoPorIdUsuarioYNumero(@PathVariable("id") Long id, @RequestBody TelefonosUsuario telefonoEliminar) {
        TelefonosUsuarioId telefonoId = new TelefonosUsuarioId(id, telefonoEliminar.getTelefono());
        if (!telefonosUsuarioRepository.existsById(telefonoId)) {
            throw new ResourceNotFoundException(
                    "No existe el teléfono " + telefonoEliminar.getTelefono() + " para el usuario con id: " + id);
        }
        telefonosUsuarioRepository.deleteById(telefonoId);
        return ResponseEntity.status(HttpStatus.OK).body(
                "El teléfono " + telefonoEliminar.getTelefono() + " del usuario con id " + id + " ha sido eliminado.");
    }
}