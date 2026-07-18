package com.grillo.practica.springboot.app.libros.springboot_libros.modules.categories.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(
        Long id,
        String nombre,
        String descripcion
) {
}
