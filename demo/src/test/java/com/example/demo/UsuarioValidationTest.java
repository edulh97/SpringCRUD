package com.example.demo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Usuario;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUsuario_Valido() {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("Cristiano Ronaldo");
        usuario.setCorreoElectronico("ronaldo@gmail.com");
        usuario.setDireccion("Calle Veronica 11");
        usuario.setContrasena("test1234");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty(), "Todo bien");
    }

    @Test
    public void testUsuario_Invalido() {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("C"); 
        usuario.setCorreoElectronico("ronaldo.gmail.com");
        usuario.setDireccion("C/");
        usuario.setContrasena("?");

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertFalse(violations.isEmpty(), "Debería estar mal");
    }

    @Test
    public void testCorreoElectronico_Valido() {
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("ronaldo@gmail.com");

        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "correoElectronico"
        );

        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación");
    }

    @Test
    public void testCorreoElectronico_Invalido() {
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("mesi.gmail.com");

        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "correoElectronico"
        );

        assertFalse(violations.isEmpty(), "Correo invalido");

        ConstraintViolation<Usuario> violation = violations.iterator().next();
        assertEquals("El correo electrónico debe ser válido, por ejemplo: usuario@dominio.com", violation.getMessage());
    }

    @Test
    public void testDireccion_Valida() {
        Usuario usuario = new Usuario();
        usuario.setDireccion("Calle Veronica 11");

        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "direccion"
        );

        assertTrue(violations.isEmpty(), "Direccion valida");
    }

    @Test
    public void testDireccion_Invalida() {
        Usuario usuario = new Usuario();
        usuario.setDireccion("C/");

        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "direccion"
        );

        assertFalse(violations.isEmpty(), "Muy corta la calle esa");

        ConstraintViolation<Usuario> violation = violations.iterator().next();
        assertEquals("La dirección debe tener entre 5 y 50 caracteres", violation.getMessage());
    }

    @Test
    public void testContrasena_Valida() {
        Usuario usuario = new Usuario();
        usuario.setContrasena("Gol1234.");

        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "contrasena"
        );

        assertTrue(violations.isEmpty(), "Contraseña 'segura'");
    }

    @Test
    public void testContrasena_Invalida() {
        Usuario usuario = new Usuario();
        usuario.setContrasena("1");

        // Valida solo el campo "contrasena"
        Set<ConstraintViolation<Usuario>> violations = validator.validateProperty(
            usuario, 
            "contrasena"
        );

        assertFalse(violations.isEmpty(), "Contraseña insegura");

        ConstraintViolation<Usuario> violation = violations.iterator().next();
        assertEquals("La contraseña debe tener entre 3 y 12 caracteres", violation.getMessage());
    }
}