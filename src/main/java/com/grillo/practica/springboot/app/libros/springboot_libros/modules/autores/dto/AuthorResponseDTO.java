package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponseDTO {
    private String nombre;
    private String nacionalidad;
}
