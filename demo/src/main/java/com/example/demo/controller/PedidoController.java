package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/kibo/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Obtener todos los pedidos
    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    // Obtener un pedido por ID
    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable("id") Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
    }

    // Crear un nuevo pedido
    @PostMapping
    public Pedido crearPedido(@Valid @RequestBody Pedido newPedido) {
        return pedidoRepository.save(newPedido);
    }

    // Actualizar un pedido existente
    @PutMapping("/{id}")
    public Pedido actualizarPedido(@Valid @PathVariable("id") Long id, @RequestBody Pedido detallesPedido) {
        Pedido updatePedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        updatePedido.setIdUsuario(detallesPedido.getIdUsuario());
        updatePedido.setEstadoPedido(detallesPedido.getEstadoPedido());
        updatePedido.setTipoEntrega(detallesPedido.getTipoEntrega());
        updatePedido.setTotal(detallesPedido.getTotal());
        updatePedido.setDetalles(detallesPedido.getDetalles());

        return pedidoRepository.save(updatePedido);
    }

    // Eliminar un pedido por ID
    @DeleteMapping("/{id}")
    public Pedido eliminarPedido(@PathVariable("id") Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
        pedidoRepository.deleteById(id);
        return pedido;
    }
}