package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Resena;
import com.example.demo.repository.ResenasRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/resenas")
public class ResenaController {

    @Autowired
    private ResenasRepository resenasRepo;

    // GET /kibo/resenas
    @GetMapping
    public List<Resena> getAllResenas() {
        return resenasRepo.findAll();
    }

    // GET /kibo/resenas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResenaById(@PathVariable Long id) {
        Optional<Resena> opt = resenasRepo.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /kibo/resenas/promedio/{idProducto}
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/promedio/{idProducto}")
    public ResponseEntity<Double> getPromedioCalificacion(@PathVariable Long idProducto) {
        Double promedio = resenasRepo.findAverageCalificacionByProductoId(idProducto);
        if (promedio == null) {
            promedio = 0.0;
        }
        return ResponseEntity.ok(promedio);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Resena>> getResenasPorProducto(@PathVariable Long idProducto) {
        List<Resena> lista = resenasRepo.findByProductoId(idProducto);
        return ResponseEntity.ok(lista);
    }

    // POST /kibo/resenas
    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestBody Resena resena) {
        // fijamos fecha de creación al recibirla
        resena.setFechaCreacion(LocalDateTime.now());
        Resena saved = resenasRepo.save(resena);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // PUT /kibo/resenas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(
            @PathVariable Long id,
            @RequestBody Resena resenaDetails) {
        Optional<Resena> opt = resenasRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Resena resena = opt.get();
        // actualizamos los campos que permitan cambio
        resena.setCalificacion(resenaDetails.getCalificacion());
        resena.setComentario(resenaDetails.getComentario());
        resena.setUsuario(resenaDetails.getUsuario());
        resena.setProducto(resenaDetails.getProducto());
        // no tocamos fechaCreacion para conservar la original

        Resena updated = resenasRepo.save(resena);
        return ResponseEntity.ok(updated);
    }

    // DELETE /kibo/resenas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        if (!resenasRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        resenasRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}