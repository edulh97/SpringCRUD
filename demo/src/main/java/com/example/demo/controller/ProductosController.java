package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductosRepository;

<<<<<<< HEAD
import jakarta.validation.Valid;

=======
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/kibo/productos")
public class ProductosController {

    @Autowired
    private ProductosRepository productosRepository;

<<<<<<< HEAD
=======
    // Para obtener todos los productos
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productosRepository.findAll();
    }

<<<<<<< HEAD
=======
    // Para obtener uno solo
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
    @GetMapping("/{id}")
    public Producto obtenerProductosPorId(@PathVariable("id") Long id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto/Plato no encontrado"));
    }

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productosRepository.save(producto));
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@Valid @PathVariable("id") Long id, @RequestBody Producto detallesProductos) {
=======
    public Producto crearProductos(@RequestBody Producto newProducto) {
        return productosRepository.save(newProducto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto detallesProductos) {
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
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