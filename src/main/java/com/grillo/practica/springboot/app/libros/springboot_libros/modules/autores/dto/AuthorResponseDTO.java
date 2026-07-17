package com.grillo.practica.springboot.app.libros.springboot_libros.modules.autores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String nombre;
    private String nacionalidad;
}
