package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.model.Categoria;
import com.example.demo.model.Producto;
import com.example.demo.repository.CategoriasRepository;
import com.example.demo.repository.ProductosRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kibo/productos")
public class ProductosController {

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductosPorId(@PathVariable("id") Long id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto/Plato no encontrado"));
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productosRepository.save(producto));
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@Valid @PathVariable("id") Long id, @RequestBody Producto detallesProductos) {
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

    @PostMapping("/{prodId}/categorias/{catId}")
    public Producto asignarCategoria(
            @PathVariable Long prodId,
            @PathVariable Long catId) {
        Producto prod = productosRepository.findById(prodId)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado " + prodId));
        Categoria cat = categoriasRepository.findById(catId)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada " + catId));

        prod.getCategorias().add(cat);
        return productosRepository.save(prod);
    }

    // 2) Quitar una categoría de un producto
    @DeleteMapping("/{prodId}/categorias/{catId}")
    public Producto quitarCategoria(
            @PathVariable Long prodId,
            @PathVariable Long catId) {
        Producto prod = productosRepository.findById(prodId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado " + prodId));
        Categoria cat = categoriasRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada " + catId));

        prod.getCategorias().remove(cat);
        return productosRepository.save(prod);
    }
}