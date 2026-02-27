package com.salonBelleza.salon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_cliente", nullable = false, length = 100)
    @NotBlank(message = "El nombre del cliente no puede estar vacío.")
    @Size(max = 100, message = "El nombre del cliente no puede tener más de 100 caracteres.")
    private String nombreCliente;

    @Column(nullable = false)
    @NotNull(message = "La fecha no puede estar vacía.")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    @NotNull(message = "Debe seleccionar un servicio.")
    private Servicio servicio;
}
