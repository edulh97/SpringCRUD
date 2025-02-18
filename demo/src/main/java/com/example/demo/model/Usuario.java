package com.example.demo.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre_completo")
    @Size(min = 2, max = 33, message = "Introduzca un nombre de mas de 2 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCompleto;

    @Column(name = "correo_electronico")
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo electrónico debe ser válido, por ejemplo: usuario@dominio.com")
    // @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "El correo electrónico debe ser válido")
    private String correoElectronico;

    @NotBlank(message = "introduce una direccion por dios que no somos adivinos")
    @Size(min = 5, max = 50, message = "La dirección debe tener entre 5 y 50 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "contrasena")
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 3, max = 12, message = "La contraseña debe tener entre 3 y 12 caracteres")
    private String contrasena;

    @Column(name = "tarjeta")
    private Long tarjeta;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<TelefonosUsuario> telefonos;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Long tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<TelefonosUsuario> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonosUsuario> telefonos) {
        this.telefonos = telefonos;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}