package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriasRepository;
import com.example.demo.ResourceNotFoundException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriasRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Long id) {
        return categoriasRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id " + id));
    }

    @PostMapping
    public Categoria crear(@Valid @RequestBody Categoria categoria) {
        return categoriasRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Categoria datos) {
        Categoria cat = categoriasRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id " + id));

        cat.setNombreCategoria(datos.getNombreCategoria());
        cat.setDescripcion(datos.getDescripcion());
        return categoriasRepository.save(cat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Categoria cat = categoriasRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id " + id));
        categoriasRepository.delete(cat);
        return ResponseEntity.ok().build();
    }
}
