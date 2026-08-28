package com.grillo.practica.springboot.app.libros.springboot_libros.modules.alquileres.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlquilerRequestDTO(
        @NotNull(message = "El id del clientes es requerido")
        Long clienteId,

        @NotNull(message = "El id del libro es requerido")
        Long boolkId,

        @NotNull(message = "La fecha de devolución prevista es requerida")
        @Future(message = "La fecha de devolución debe ser una fecha futura")
        LocalDate fechaDevolucionPrevista
) {
}
