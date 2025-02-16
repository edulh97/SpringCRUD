package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

// La razón de esta clase es que según las convenciones de JPA, no podemos definir 
// una clave primaria compuesta directamente en la misma clase de la entidad... si

public class TelefonosUsuarioId implements Serializable {

    private Long idUsuario;
    private String telefono;

    public TelefonosUsuarioId() {}

    public TelefonosUsuarioId(Long idUsuario, String telefono) {
        this.idUsuario = idUsuario;
        this.telefono = telefono;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefonosUsuarioId that = (TelefonosUsuarioId) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
               Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, telefono);
    }
}
