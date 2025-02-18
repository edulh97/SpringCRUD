package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.TelefonosUsuario;
import com.example.demo.model.TelefonosUsuarioId;
import com.example.demo.repository.TelefonosUsuarioRepository;

=======
import org.springframework.web.bind.annotation.*;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.TelefonosUsuario;
import com.example.demo.repository.TelefonosUsuarioRepository;
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
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

<<<<<<< HEAD
    @GetMapping("/{id}")
    public List<TelefonosUsuario> obtenerTelefonosPorIdUsuario(@PathVariable("id") Long id) {
        return telefonosUsuarioRepository.findByIdUsuario(id);
    }

=======
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
    @PostMapping
    public TelefonosUsuario crearTelefono(@RequestBody TelefonosUsuario newTelefono) {
        return telefonosUsuarioRepository.save(newTelefono);
    }

<<<<<<< HEAD
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
=======
    @DeleteMapping("/{id}")
    public TelefonosUsuario eliminarTelefono(@PathVariable("id") Long id) {
        TelefonosUsuario telefonosUsuario = telefonosUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un teléfono con el ID: " + id));

        telefonosUsuarioRepository.deleteById(id);
        return telefonosUsuario;
    }

    @PutMapping("/{id}")
    public TelefonosUsuario modificarTelefono(@PathVariable("id") Long id,
            @RequestBody TelefonosUsuario modificarTelefono) {
        TelefonosUsuario telefonosUsuario = telefonosUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ese telefono con esa id"));
        telefonosUsuario.setTelefono(modificarTelefono.getTelefono());

        return telefonosUsuarioRepository.save(telefonosUsuario);
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
    }
}