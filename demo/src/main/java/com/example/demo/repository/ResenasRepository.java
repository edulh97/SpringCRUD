package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Resena;

public interface ResenasRepository extends JpaRepository<Resena, Long> {

    @Query("SELECT AVG(r.calificacion) " +
            "FROM Resena r " +
            "WHERE r.producto.id = :idProducto")
    Double findAverageCalificacionByProductoId(@Param("idProducto") Long idProducto);

    List<Resena> findByProductoId(Long productoId);

}
