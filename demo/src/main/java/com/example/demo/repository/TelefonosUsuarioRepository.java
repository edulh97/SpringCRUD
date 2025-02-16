package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TelefonosUsuario;
import com.example.demo.model.TelefonosUsuarioId;

import java.util.List;

@Repository
public interface TelefonosUsuarioRepository extends JpaRepository<TelefonosUsuario, TelefonosUsuarioId> {
    List<TelefonosUsuario> findByIdUsuario(Long idUsuario);
}