package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    @Min(value = 1, message = "El ID de usuario debe ser un número positivo")
    private Long idUsuario;

    @CreationTimestamp
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "estado_pedido")
    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(max = 50, message = "El estado del pedido no puede exceder los 50 caracteres")
    @Pattern(regexp = "^(PENDIENTE|ENVIADO|ENTREGADO|CANCELADO)$", message = "Estado inválido. Valores permitidos: PENDIENTE, ENVIADO, ENTREGADO, CANCELADO")
    private String estadoPedido;

    @Column(name = "tipo_entrega", nullable = false, length = 255)
    @NotBlank(message = "El tipo de entrega es obligatorio")
    @Pattern(regexp = "^(DOMICILIO|RECOGIDA)$", message = "Tipo de entrega inválido. Valores permitidos: DOMICILIO, RECOGIDA")
    private String tipoEntrega;

    @Column(name = "total")
    @Digits(integer = 10, fraction = 2, message = "El total debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal total;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotEmpty(message = "El pedido debe tener al menos un detalle")
    private List<DetallePedido> detalles;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}