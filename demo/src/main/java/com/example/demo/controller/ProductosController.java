package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductosRepository;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/kibo/productos")
public class ProductosController {

    @Autowired
    private ProductosRepository productosRepository;

    // Para obtener todos los productos
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productosRepository.findAll();
    }

    // Para obtener uno solo
    @GetMapping("/{id}")
    public Producto obtenerProductosPorId(@PathVariable("id") Long id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto/Plato no encontrado"));
    }

    @PostMapping
    public Producto crearProductos(@RequestBody Producto newProducto) {
        return productosRepository.save(newProducto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto detallesProductos) {
        Producto updateProducto = productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto/Plato no encontrado"));

        updateProducto.setNombreProducto(detallesProductos.getNombreProducto());
        updateProducto.setDescripcion(detallesProductos.getDescripcion());
        updateProducto.setPrecio(detallesProductos.getPrecio());
        updateProducto.setAlergenos(detallesProductos.getAlergenos());

        return productosRepository.save(updateProducto);
    }

    @DeleteMapping("/{id}")
    public Producto eliminarProducto(@PathVariable("id") Long id) {
        Producto producto = productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no existe"));
        productosRepository.deleteById(id);
        return producto;
    }
}