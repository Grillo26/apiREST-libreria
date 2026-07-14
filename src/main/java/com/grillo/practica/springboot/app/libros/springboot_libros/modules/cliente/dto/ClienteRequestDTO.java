package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 100)
    private String nombre;

    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20)
    private String telefono;

}
