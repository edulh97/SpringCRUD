package com.example.demo;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.controller.UsuariosController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsuariosControllerTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private UsuariosController usuariosController;

    @Test
    public void testObtenerTodosLosUsuarios() {
        Usuario usuario1 = new Usuario();
        usuario1.setNombreCompleto("Juan");
        Usuario usuario2 = new Usuario();
        usuario2.setNombreCompleto("Ana");

        when(usuariosRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Llamada del metodo
        List<Usuario> usuarios = usuariosController.obtenerTodosLosUsuarios();

        assertEquals(2, usuarios.size());
        assertEquals("Juan", usuarios.get(0).getNombreCompleto());
        assertEquals("Ana", usuarios.get(1).getNombreCompleto());
    }

    @Test
    public void testObtenerUsuarioPorId() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreCompleto("Juan");

        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuariosController.obtenerUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombreCompleto());
    }

    @Test
    public void testCrearUsuario() {
        Usuario newUsuario = new Usuario();
        newUsuario.setCorreoElectronico("Juan@gmail.com");

        when(usuariosRepository.save(any(Usuario.class))).thenReturn(newUsuario);

        // 2. Llamar al método del controlador
        Usuario usuarioCreado = usuariosController.crearUsuario(newUsuario);

        assertNotNull(usuarioCreado);
        assertEquals("Juan@gmail.com", usuarioCreado.getCorreoElectronico());
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNombreCompleto("Juan");

        Usuario detallesUsuario = new Usuario();
        detallesUsuario.setNombreCompleto("Juan Pérez");

        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuariosRepository.save(any(Usuario.class))).thenReturn(usuarioExistente);

        // Llamada al método
        Usuario usuarioActualizado = usuariosController.actualizarUsuario(1L, detallesUsuario);

        assertNotNull(usuarioActualizado);
        assertEquals("Juan Pérez", usuarioActualizado.getNombreCompleto());
    }

    @Test
    public void testEliminarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreCompleto("Juan");

        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuariosRepository).deleteById(1L);

        // 2. Llamar al método del controlador
        Usuario usuarioEliminado = usuariosController.eliminarUsuario(1L);

        assertNotNull(usuarioEliminado);
        assertEquals("Juan", usuarioEliminado.getNombreCompleto());
    }

}