package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Long> {

    
}