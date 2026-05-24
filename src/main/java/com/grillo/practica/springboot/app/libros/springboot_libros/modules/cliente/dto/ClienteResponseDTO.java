package com.grillo.practica.springboot.app.libros.springboot_libros.modules.cliente.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDateTime fechaRegistro;
}
