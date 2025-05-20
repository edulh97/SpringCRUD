package com.example.demo.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.DetallePedido;
import com.example.demo.repository.DetallePedidoRepository;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    // Obtener TODOS los detalles (para administración)
    @GetMapping
    public List<DetallePedido> obtenerTodosLosDetalles() {
        return detallePedidoRepository.findAll();
    }

    // Obtener detalles por ID de PEDIDO (endpoint principal)
    @GetMapping("/{idPedido}")
    public List<DetallePedido> obtenerDetallesPorPedido(
            @PathVariable("idPedido") Long idPedido) {
        return detallePedidoRepository.findByPedidoId(idPedido);
    }

    // Obtener un detalle específico por SU ID (para casos especiales)
    @GetMapping("/detalle/{idDetalle}")
    public DetallePedido obtenerDetallePorId(
            @PathVariable("idDetalle") Long idDetalle) {
        return detallePedidoRepository.findById(idDetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
    }

    @PostMapping
    public ResponseEntity<DetallePedido> crearDetalle(
            @RequestBody DetallePedido detalle) {

        // Validación básica
        if (detalle.getIdProducto() == null || detalle.getCantidad() == null
                || detalle.getPrecioUnitario() == null || detalle.getPedido() == null) {
            throw new IllegalArgumentException("Faltan campos requeridos");
        }

        // El subtotal y total se calculan automáticamente en la BD
        DetallePedido detalleGuardado = detallePedidoRepository.save(detalle);

        return ResponseEntity.created(URI.create("/kibo/detalles-pedido/" + detalleGuardado.getIdDetalle()))
                .body(detalleGuardado);
    }

    @PutMapping("/{idDetalle}")
    public DetallePedido actualizarDetalle(
            @PathVariable("idDetalle") Long idDetalle,
            @Valid @RequestBody DetallePedido detalleActualizado) {
        DetallePedido detalle = detallePedidoRepository.findById(idDetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));

        detalle.setCantidad(detalleActualizado.getCantidad());
        detalle.setPrecioUnitario(detalleActualizado.getPrecioUnitario());
        detalle.setSubtotal(detalle.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(detalle.getCantidad())));

        return detallePedidoRepository.save(detalle);
    }

    @DeleteMapping("/{idDetalle}")
    public DetallePedido eliminarDetalle(@PathVariable("idDetalle") Long idDetalle) {
        DetallePedido detalle = detallePedidoRepository.findById(idDetalle)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado"));
        detallePedidoRepository.deleteById(idDetalle);
        return detalle;
    }
}