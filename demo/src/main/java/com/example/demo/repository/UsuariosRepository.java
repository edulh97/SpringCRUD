package com.example.demo.repository;

<<<<<<< HEAD
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long>{

}
>>>>>>> 390eb3efa0dfe9b46e968d3b38e609e8270c5087
