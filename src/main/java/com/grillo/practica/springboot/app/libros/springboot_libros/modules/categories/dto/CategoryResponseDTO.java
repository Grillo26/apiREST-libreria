package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(
        String nombre,
        String descripcion
) {
}
