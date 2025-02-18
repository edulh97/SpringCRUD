package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TelefonosUsuario;
<<<<<<< HEAD
import com.example.demo.model.TelefonosUsuarioId;

import java.util.List;

@Repository
public interface TelefonosUsuarioRepository extends JpaRepository<TelefonosUsuario, TelefonosUsuarioId> {
    List<TelefonosUsuario> findByIdUsuario(Long idUsuario);
}
=======

@Repository
public interface TelefonosUsuarioRepository extends JpaRepository<TelefonosUsuario, Long>{
    
}
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
