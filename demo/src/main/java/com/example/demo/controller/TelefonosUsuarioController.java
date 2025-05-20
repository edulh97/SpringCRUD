package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.TelefonosUsuario;
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

    @PostMapping
    public TelefonosUsuario crearTelefono(@RequestBody TelefonosUsuario newTelefono) {
        return telefonosUsuarioRepository.save(newTelefono);
    }

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
    }
}